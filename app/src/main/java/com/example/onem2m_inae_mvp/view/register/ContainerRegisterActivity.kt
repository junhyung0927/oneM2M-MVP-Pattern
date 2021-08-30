package com.example.onem2m_inae_mvp.view.register

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityContainerRegisterBinding
import com.example.onem2m_inae_mvp.util.setContainerImageId
import com.example.onem2m_inae_mvp.view.main.INAEActivity
import com.example.onem2m_inae_mvp.view.main.INAEContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.FieldPosition

class ContainerRegisterActivity() : BaseActivity<ActivityContainerRegisterBinding>(),
    ContainerRegisterContract.View {
    override val presenter: ContainerRegisterPresenter by inject { parametersOf(this) }

    companion object {
        private var pos: Int = -1
        private var containerImage = 0
        private val containerType = listOf(
            ContainerType.AIRCONDITIONAL,
            ContainerType.AIRPURIFIER,
            ContainerType.BOILER,
        )
    }

    override val layoutId: Int
        get() = R.layout.activity_container_register

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityContainerRegisterBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addTextChange()
        spinnerCreateFromResource()
        spinnerItemSelected()
        addRegisterContainer()
    }

    private fun addTextChange() {
        binding.apply {
            textInputEditContainerNameRegisterActivity.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    if (textInputEditContainerNameRegisterActivity.text!!.isEmpty()) {
                        textInputEditContainerNameRegisterActivity.error = "다시 입력해주세요."
                    } else {
                        textInputEditContainerNameRegisterActivity.error = null
                    }
                }
            })
        }
    }

    private fun spinnerCreateFromResource() {
        ArrayAdapter.createFromResource(
            this,
            R.array.container_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerContainerImageSelectRegisterActivity.adapter = adapter
        }
    }

    private fun spinnerItemSelected() {
        binding.apply {
            spinnerContainerImageSelectRegisterActivity.onItemSelectedListener =
                object: AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                        imageViewContainerImageRegisterActivity.setImageResource(containerType.get(position).resId)
                        containerImage = containerType.get(position).resId
                        pos = position
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) { }

                    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) { }

                }
        }
    }

    private fun addRegisterContainer() {
        binding.apply {
            buttonContainerAddRegisterActivity.setOnClickListener {
                presenter.apply {
                    registerContainer(
                        containerImage,
                        binding.textInputEditContainerNameRegisterActivity.text.toString(),
                        containerType.get(pos)
                    )
                }
            }
        }
    }


    override fun showINAEActivity() {
        startActivity(Intent(this,  INAEActivity::class.java))
    }
}