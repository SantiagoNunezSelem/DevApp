//package com.example.myapplication4.ui.transaccion
//
//import android.app.DatePickerDialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.widget.addTextChangedListener
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.myapplication4.ui.adapters.CategoryAdapter
//import com.example.myapplication4.databinding.FragmentTransactionEditBinding
//import com.example.myapplication4.repository.CategoryRepository
//import com.example.myapplication4.repository.TransactionRepository
//import com.google.android.material.snackbar.Snackbar
//import com.google.android.material.tabs.TabLayout
//import java.text.SimpleDateFormat
//import java.util.Calendar
//import java.util.Locale
//
//class TransactionEditFragment : Fragment() {
//
//    private var _binding: FragmentTransactionEditBinding? = null
//    private val binding get() = _binding!!
////    private lateinit var viewModel: TransaccionViewModel
//    private lateinit var categoryAdapter: CategoryAdapter
//
//    private val viewModel: TransactionEditViewModel by viewModels {
//        TransactionEditViewModelFactory(CategoryRepository(), TransactionRepository())
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentTransactionEditBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupTabLayout()
//        setupCategoryRecyclerView()
//        setupAmountInput()
//        setupDateInput()
//        setupInstallmentsInput()
//        setupInterestRateInput()
//        setupAddButton()
//    }
//
//    private fun setupTabLayout() {
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                // Cambiar entre gastos e ingresos
//                when (tab?.position) {
//                    0 -> {
//                        viewModel.setTransactionType(TransactionType.EXPENSE)
//                        viewModel.showExpenseCategories()
//                        binding.tilCuotas.visibility = View.VISIBLE
//                        binding.tilInteres.visibility = View.VISIBLE
//                    }
//
//                    1 -> {
//                        viewModel.setTransactionType(TransactionType.INCOME)
//                        viewModel.showIncomeCategories()
//                        binding.tilCuotas.visibility = View.GONE
//                        binding.tilInteres.visibility = View.GONE
//                    }
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })
//    }
//
//    private fun setupCategoryRecyclerView() {
//        categoryAdapter = CategoryAdapter { category ->
//            viewModel.setSelectedCategory(category)
//        }
//        binding.rvCategories.apply {
//            layoutManager = GridLayoutManager(context, 4)
//            adapter = categoryAdapter
//        }
//
//        // Observar cambios en las categorías
//        viewModel.currentCategories.observe(viewLifecycleOwner) { categories ->
//            categoryAdapter.submitList(categories)
//        }
//    }
//
//    private fun setupAmountInput() {
//        binding.etAmount.addTextChangedListener { editable ->
//            editable?.toString()?.let { amount ->
//                if (amount.isNotEmpty()) {
//                    viewModel.setAmount(amount.toDouble())
//                }
//            }
//        }
//    }
//
//    private fun setupDateInput() {
//        binding.etDate.setOnClickListener { showDatePickerDialog() }
//
//        viewModel.selectedDate.observe(viewLifecycleOwner) { calendar ->
//            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            binding.etDate.setText(dateFormat.format(calendar.time))
//        }
//    }
//
//    private fun setupInstallmentsInput() {
//        binding.etCuotas.addTextChangedListener { editable ->
//            editable?.toString()?.toIntOrNull()?.let { installments ->
//                viewModel.setInstallments(installments)
//            }
//        }
//    }
//
//    private fun setupInterestRateInput() {
//        binding.etInteres.addTextChangedListener { editable ->
//            editable?.toString()?.toDoubleOrNull()?.let { rate ->
//                viewModel.setInterestRate(rate)
//            }
//        }
//    }
//
//    private fun showDatePickerDialog() {
//        val calendar = viewModel.selectedDate.value ?: Calendar.getInstance()
//        DatePickerDialog(
//            requireContext(),
//            { _, year, month, dayOfMonth ->
//                viewModel.setSelectedDate(year, month, dayOfMonth)
//            },
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//        ).show()
//    }
//
//    private fun setupAddButton() {
//        binding.btnSaveTransaction.setOnClickListener {
//            val amount = binding.etAmount.text.toString().toDoubleOrNull()
//            val comment = binding.tilComment.editText?.text.toString()
//            val date = viewModel.selectedDate.value
//            val installments = binding.etCuotas.text.toString().toIntOrNull() ?: 1
//            val interestRate = binding.etInteres.text.toString().toDoubleOrNull() ?: 0.0
//
//            if (amount != null && comment.isNotBlank() && viewModel.selectedCategory.value != null && date != null) {
//                viewModel.addTransaction(amount, comment, date, installments, interestRate)
//                clearFields()
//                showSuccessMessage()
//                requireActivity().supportFragmentManager.popBackStack() // Go back to previous fragment
//            } else {
//                showErrorMessage()
//            }
//        }
//    }
//
//    private fun clearFields() {
//        binding.etAmount.text.clear()
//        binding.tilComment.editText?.text?.clear()
//        binding.etCuotas.text?.clear()
//        binding.etInteres.text?.clear()
//        viewModel.setSelectedDate(
//            Calendar.getInstance().get(Calendar.YEAR),
//            Calendar.getInstance().get(Calendar.MONTH),
//            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        )
//    }
//
//    private fun showSuccessMessage() {
//        Snackbar.make(binding.root, "Transacción añadida con éxito", Snackbar.LENGTH_SHORT).show()
//    }
//
//    private fun showErrorMessage() {
//        Snackbar.make(binding.root, "Por favor, completa todos los campos", Snackbar.LENGTH_SHORT)
//            .show()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}