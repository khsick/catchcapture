package com.abangfadli.shotwatchapp.View

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.abangfadli.shotwatchapp.*
import com.abangfadli.shotwatchapp.base.BaseActivity
import com.abangfadli.shotwatchapp.filters.FilterListener
import com.abangfadli.shotwatchapp.util.FileUtils
import com.bumptech.glide.Glide
import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity
import iamutkarshtiwari.github.io.ananas.editimage.ImageEditorIntentBuilder

import kotlinx.android.synthetic.main.activity_image_edit_test.*
import java.lang.Exception


class ImageEditTest : BaseActivity(), View.OnClickListener,FilterListener {

    private val PHOTO_EDITOR_REQUEST_CODE = 231// Any integer value as a request code.
    val ACTION_REQUEST_EDITIMAGE = 9


    private val TAG = ImageEditTest::class.java!!.getSimpleName()
    val EXTRA_IMAGE_PATHS = "extra_image_paths"
    private val CAMERA_REQUEST = 52
    private val PICK_REQUEST = 53

    private var mRootView: ConstraintLayout? = null
    private val mConstraintSet = ConstraintSet()
    private var mIsFilterVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeFullScreen()

    }

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onBackPressed() {
        if (mIsFilterVisible) {

        } else {
            super.onBackPressed()
        }
    }

}
