package com.example.addcardapp

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.example.addcardapp.databinding.ActivityAddCardBinding
import com.example.addcardapp.utils.Helper
import kotlinx.android.synthetic.main.activity_add_card.*
import java.util.*


class AddCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCardBinding

    private lateinit var frontFlipAnim : AnimatorSet
    private lateinit var backFlipAnim : AnimatorSet
    private lateinit var frontPushUpFlip : AnimatorSet
    private lateinit var frontPushDownFlip : AnimatorSet
    var isFront = true

    private val INITIAL_MONTH_ADD_ON = "0"
    private val DEFAULT_MONTH = "01"
    private val SPACE = "/"
    private var mLength = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val aniSlide: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up_400)

        binding.cardNumberInputLayout.startAnimation(aniSlide)

        binding.rootLayout.setOnClickListener {
            Helper.hideSoftKeyboard(this)
        }

        val scale = resources.displayMetrics.density
        val cameraDist = 8000 * scale
        frontCardRelativeLayout.cameraDistance = cameraDist
        backCardRelativeLayout.cameraDistance = cameraDist

        frontFlipAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.flip_out) as AnimatorSet
        backFlipAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.flip_in) as AnimatorSet

        frontPushUpFlip = AnimatorInflater.loadAnimator(applicationContext, R.animator.flip_down) as AnimatorSet
        frontPushDownFlip = AnimatorInflater.loadAnimator(applicationContext, R.animator.flip_up) as AnimatorSet

        frontPushUpFlip.setTarget(relativeLayout)
        frontPushDownFlip.setTarget(relativeLayout)
        frontPushUpFlip.start()
        frontPushDownFlip.start()


        binding.cardNumberInput.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.cardNumberInput.removeTextChangedListener(this)
                val cp = binding.cardNumberInput.selectionStart
                val initialLength = binding.cardNumberInput.text?.length
                binding.cardNumber.text = Helper.formatCard(Helper.generateNumberStars(Helper.stripAwayWhiteSpaces(s.toString())))
                binding.cardNumberInput.setText(Helper.formatCard(Helper.stripAwayWhiteSpaces(s.toString())))

                val endLength = binding.cardNumberInput.text?.length
                val sel = (cp + (endLength!! - initialLength!!))
                if (sel > 0 && sel <= binding.cardNumberInput.text?.length!!) {
                    binding.cardNumberInput.setSelection(sel)
                }

                if (Helper.stripAwayWhiteSpaces(binding.cardNumberInput.text.toString()).length == 16) {
                    Helper.hideSoftKeyboard(this@AddCardActivity)
                    binding.cardNumberInputLayout.visibility = View.GONE
                    binding.cardHolderInputLayout.startAnimation(aniSlide)
                    binding.cardHolderInputLayout.visibility = View.VISIBLE

                }

                binding.cardNumberInput.addTextChangedListener(this)
            }
        })

        binding.cardExpiryInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mLength = binding.cardExpiryInput.text?.length!!
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.cardExpiryInput.removeTextChangedListener(this)
                val currentLength: Int = binding.cardExpiryInput.text?.length!!
                var ignoreBecauseIsDeleting = false
                if (mLength > currentLength) {
                    ignoreBecauseIsDeleting = true
                }

                if (ignoreBecauseIsDeleting && s.toString().startsWith(SPACE)) {
                    return
                }

                if (s?.length == 1 && !isNumberChar(s[0].toString())) {
                    binding.expiry.setText(DEFAULT_MONTH.plus(SPACE))
                    binding.cardExpiryInput.setText(DEFAULT_MONTH.plus(SPACE))


                } else if (s?.length == 1 && !isCharValidMonth(s[0])) {
                    binding.expiry.setText(INITIAL_MONTH_ADD_ON.plus(s[0].toString()).plus(SPACE))
                    binding.cardExpiryInput.setText(INITIAL_MONTH_ADD_ON.plus(s[0].toString()).plus(SPACE))

                } else if (s?.length == 2 && s[s.length - 1].toString() == SPACE) {
                    binding.expiry.setText(INITIAL_MONTH_ADD_ON.plus(s.toString()))
                    binding.cardExpiryInput.setText(INITIAL_MONTH_ADD_ON.plus(s.toString()))

                } else if (!ignoreBecauseIsDeleting &&
                    (s?.length == 2 && s[s.length - 1].toString() != SPACE)) {
                    binding.expiry.setText(binding.cardExpiryInput.text.toString().plus(SPACE))
                    binding.cardExpiryInput.setText(binding.cardExpiryInput.text.toString().plus(SPACE))

                } else if (s?.length == 3 && s[s.length - 1].toString() != SPACE && !ignoreBecauseIsDeleting) {
                    s.insert(2, SPACE)
                    binding.expiry.setText(s.toString())
                    binding.cardExpiryInput.setText(s.toString())


                } else if (s?.length!! > 3 && !isCharValidMonth(s[0])) {
                    binding.expiry.setText(INITIAL_MONTH_ADD_ON.plus(s))
                    binding.cardExpiryInput.setText(INITIAL_MONTH_ADD_ON.plus(s))

                } else {
                    binding.expiry.setText(s.toString())
                }

                if (!ignoreBecauseIsDeleting) {
                    binding.cardExpiryInput.setSelection(binding.cardExpiryInput.text.toString().length)
                }

                if(currentLength == 5){
                    Helper.hideSoftKeyboard(this@AddCardActivity)
                    binding.cardExpiryInputLayout.visibility = View.GONE
                    binding.cardCVVInputLayout.startAnimation(aniSlide)
                    binding.cardCVVInputLayout.visibility = View.VISIBLE
                    flip()
                }


                binding.cardExpiryInput.addTextChangedListener(this)
            }

        })

        binding.cardHolderInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                binding.cardHolderName.text = p0

            }

        })

        binding.cardHolderInput.setOnEditorActionListener { p0, p1, p2 ->
            if (p1 == EditorInfo.IME_ACTION_DONE) {
                Helper.hideSoftKeyboard(this@AddCardActivity)
                binding.cardHolderInputLayout.visibility = View.GONE
                binding.cardExpiryInputLayout.startAnimation(aniSlide)
                binding.cardExpiryInputLayout.visibility = View.VISIBLE

                true
            }

            false
        }
    }

    private fun isCharValidMonth(charFromString: Char): Boolean {
        var month = 0
        if (Character.isDigit(charFromString)) {
            month = charFromString.toString().toInt()
        }
        return month <= 1
    }

    private fun isNumberChar(string: String): Boolean {
        return string.matches(".*\\d.*".toRegex())
    }

    private fun flip() {
        isFront = if (isFront) {
            frontFlipAnim.setTarget(frontCardRelativeLayout)
            backFlipAnim.setTarget(backCardRelativeLayout)
            frontFlipAnim.start()
            backFlipAnim.start()
            false
        } else {
            frontFlipAnim.setTarget(backCardRelativeLayout)
            backFlipAnim.setTarget(frontCardRelativeLayout)
            backFlipAnim.start()
            frontFlipAnim.start()
            true
        }
    }
}