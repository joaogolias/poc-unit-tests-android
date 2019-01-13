package com.example.joaogolias.pocunittests.components.customEditText

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import android.view.View
import android.widget.TextView
import com.example.joaogolias.pocunittests.R
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric


@RunWith(RobolectricTestRunner::class)
class CustomEditTextTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var attributes: AttributeSet

    @Mock
    private lateinit var typedArray: TypedArray

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

//    private lateinit var customEditText: CustomEditText
//    private lateinit var errorTv: TextView
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this);
////        val activityController = Robolectric.buildActivity(Activity::class.java)
//        context = mock(Context::class.java)
////        attributes = AttributeSet()
////        doReturn(typedArray).when(context).obtainStyledAttributes(attributes, R.styleable.CustomEditText)
//        doReturn(typedArray).`when`(context).obtainStyledAttributes(attributes, R.styleable.CustomEditText)
//        doReturn(true).`when`(typedArray).getBoolean(R.styleable.CustomEditText_emptinessIsValid, false)
////        `when`(context.obtainStyledAttributes(attributes, R.styleable.CustomEditText)).thenReturn(typedArray)
////        whenever(typedArray.getBoolean(R.styleable.CustomEditText_emptinessIsValid, false)).thenReturn(true)
////        customEditText = CustomEditText(context, attributes)
////        errorTv = customEditText.findViewById(R.id.errorTv)
//    }
//
//    @Test
//    fun `displayErrorTv sets errorTv visibility as Visible, and sets its text`() {
//        val customEditText = CustomEditText(context, attributes)
//        val errorTv = customEditText.findViewById<TextView>(R.id.errorTv)
//        customEditText.displayErrorTv(true, "Text")
//        assert(errorTv.visibility == View.VISIBLE)
//        assert(errorTv.text == "Text")
//    }

}

