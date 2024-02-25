package com.example.week_2_assignment2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week_2_assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private var chemicalNameList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.addChemicalButton.setOnClickListener {
            val newChemical = mainBinding.newChemicalEditText.text.toString().trim()
            if (newChemical.isNotEmpty()){
                if (chemicalNameList.contains(newChemical)){
                    mainBinding.chemicalTextView.text = getString(R.string.chemical_is_already_available, newChemical)
                }else{
                    chemicalNameList.add(newChemical)
                    mainBinding.chemicalTextView.text = getString(R.string.chemical_added_successfully, newChemical)
                }
            }else{
                Toast.makeText(this,getString(R.string.new_chemical_field_cannot_be_empty), Toast.LENGTH_SHORT).show()
            }
        }

        mainBinding.guessButton.setOnClickListener {
            val guessChemical = mainBinding.guessChemicalNameEditText.text.toString().trim()
            if (guessChemical.isNotEmpty()){
                if (chemicalNameList.isNotEmpty()) {
                    val randomChemical = chemicalNameList.random()
                    if (guessChemical.equals(randomChemical, ignoreCase = true)) {
                        mainBinding.chemicalTextView.text = getString(R.string.congratulations_you_guessed_it_right_it_was, guessChemical)
                    } else {
                        mainBinding.chemicalTextView.text = getString(R.string.sorry_wrong_guess_the_correct_answer_was, randomChemical)
                    }
                }else{
                    Toast.makeText(this,getString(R.string.please_add_new_chemical_to_guess_it), Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,getString(R.string.guess_chemical_field_cannot_be_empty), Toast.LENGTH_SHORT).show()
            }
        }
    }
}