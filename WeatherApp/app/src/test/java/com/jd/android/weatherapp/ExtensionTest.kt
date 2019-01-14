package com.jd.android.weatherapp

import com.jd.android.weatherapp.extensions.toDateString
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

class ExtensionTest {

    @Test
    fun `"longToDateString" retuns valid value`() {
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test
    fun `"longToDateString" with full format returns valid value`() {
        assertEquals("Monday, October 19, 2015", 1445275635000L.toDateString(DateFormat.FULL))
    }
}