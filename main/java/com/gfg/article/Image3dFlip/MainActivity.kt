package com.gfg.article.Image3dFlip


import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    private lateinit var animation1: Animation
    private lateinit var animation2: Animation
    private var isFrontOfCardShowing = true
    private lateinit var image: ImageView
    private lateinit var clickBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animation1 =
            AnimationUtils.loadAnimation(this, R.anim.to_middle) //apply animation from to_middle
        animation1.setAnimationListener(this)
        animation2 =
            AnimationUtils.loadAnimation(this, R.anim.from_middle)  //apply animation from to_middle
        animation2.setAnimationListener(this)
        clickBtn = findViewById(R.id.button1)
        image = findViewById(R.id.imageView1)

        clickBtn.setOnClickListener {
            it.isEnabled = false
            image.clearAnimation() //stop animation
            image.animation = animation1
            image.startAnimation(animation1) //start the animation
        }
    }


    override fun onAnimationEnd(animation: Animation) {
        if (animation === animation1) {
            //check whether the front of the card is showing
            if (isFrontOfCardShowing) {
                image.setImageResource(R.drawable.card_back) //set image from card_front to card_back
            } else {
                image.setImageResource(R.drawable.card_front) //set image from card_back to card_front
            }
            image.clearAnimation()//stop the animation of the ImageView
            image.animation = animation2
            image.startAnimation(animation2) //allow fine-grained control over the start time and invalidation
        } else {
            isFrontOfCardShowing = !isFrontOfCardShowing
            clickBtn.isEnabled = true
        }
    }

    override fun onAnimationRepeat(animation: Animation?) {
        // TODO Auto-generated method stub
    }

    override fun onAnimationStart(animation: Animation?) {
        // TODO Auto-generated method stub
    }

}