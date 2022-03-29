package com.example.firstaiduidesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.firstaiduidesign.databinding.ActivityFirstAidInformationBinding

class FirstAidInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstAidInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstAidInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        Toast.makeText(this, "This is first aid info activity", Toast.LENGTH_SHORT).show()

        binding.cardThingsToDo.setOnClickListener {
            when (binding.tvFirstAidContentBody.visibility){
                /*  if the visibility of content body is either GONE or INVISIBLE
                    sets the visibility to VISIBLE and then performs an expanding animation
                 */
                View.GONE -> {
                    binding.tvFirstAidContentBody.visibility = View.VISIBLE
                    binding.cardIcon.rotation = 90F
                    expand(binding.tvFirstAidContentBody)
                }
                View.INVISIBLE -> {
                    binding.tvFirstAidContentBody.visibility = View.VISIBLE
                    expand(binding.tvFirstAidContentBody)
                }
                // Performs a collapsing animation if the content body is visible then sets its
                // visibility to gone
                View.VISIBLE -> {
                    binding.tvFirstAidContentBody.visibility = View.GONE
                    binding.cardIcon.rotation = 0F
                    collapse(binding.tvFirstAidContentBody)
                }

            }
        }

        // Going back to the previous activity stack
        binding.toolBFirstAidInfoToolBar.setNavigationOnClickListener{
            onBackPressed()
        }
    }

    private fun collapse(view: View){
        val initialHeight: Int = view.measuredHeight

        val collapsingAnimation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                super.applyTransformation(interpolatedTime, t)
                if(interpolatedTime == 1F){
                    view.visibility = View.GONE
                } else {
                    view.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        collapsingAnimation.duration = (initialHeight / view.context.resources.displayMetrics.density).toLong()
    }

    private fun expand(view: View){
        val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec((view.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight: Int = view.measuredHeight

        view.layoutParams.height = 1
        view.visibility = View.VISIBLE

        val animationObj: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                super.applyTransformation(interpolatedTime, t)
                view.layoutParams.height = if (interpolatedTime.toInt() == 1) LinearLayout.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        animationObj.duration = (targetHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(animationObj)
    }

//    companion object Animation : android.view.animation.Animation() {
//        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
//            super.applyTransformation(interpolatedTime, t)
//
//        }
//    }
}
