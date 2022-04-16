package com.avidaldo.android_tictactoe2.ui.tresenrayaview

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.avidaldo.android_tictactoe2.R
import com.avidaldo.android_tictactoe2.databinding.FragmentTresEnRayaBinding

class TresEnRayaViewFragment : Fragment() {

    companion object {
        fun newInstance() = TresEnRayaViewFragment()
    }

    private var _binding: FragmentTresEnRayaBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTresEnRayaBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    /*************************************************************************/

    private val viewModel: TresEnRayaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGrid.setOnClickListener(::onCellClicked)


        viewModel.modelLiveData.observe(viewLifecycleOwner) {

            binding.btn00.text = it.tablero[0][0].toString()
            binding.btn01.text = it.tablero[0][1].toString()
            binding.btn02.text = it.tablero[0][2].toString()
            binding.btn10.text = it.tablero[1][0].toString()
            binding.btn11.text = it.tablero[1][1].toString()
            binding.btn12.text = it.tablero[1][2].toString()
            binding.btn20.text = it.tablero[2][0].toString()
            binding.btn21.text = it.tablero[2][1].toString()
            binding.btn22.text = it.tablero[2][2].toString()


            binding.winnerPlayerViewGroup.visibility =
                it.ganador?.let {
                    binding.winnerPlayerLabel.text = it.toString()
                    View.VISIBLE
                } ?: View.GONE

        }
    }


    /***************************************************************************************
     * Definición del menú de reset
     ***************************************************************************************/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("---", "onOptionsItemSelected fragment")
        return when (item.itemId) {
            R.id.action_reset -> {
                viewModel.onResetSelected(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    /***************************************************************************************
     * Definición del comportamiento de la vista ante eventos (cuando se tocan las celdas)
     ***************************************************************************************/

    /** Método que indica al viewModel qué celda se ha pulsado */
    private fun onCellClicked(button: Button) {
        val tag = button.tag.toString().toCharArray()
        val row = tag[0].digitToInt()
        val col = tag[1].digitToInt()
        viewModel.onButtonSelected(row, col)
    }


    /** Función de extensión que recibe como parámetro una lambda
     * Se extiende GridLayout para que tenga este método, el cual recorre todos los Button que la componen (asunción) y para cada uno de ellos le
     * asigna la función pasada a su OnClickListener */
    private fun GridLayout.setOnClickListener(onClick: (Button) -> Unit) {
        for (i in 0 until childCount) {
            val boton = getChildAt(i) as Button
            boton.setOnClickListener {
                onClick(boton)
            }
        }
    }


}