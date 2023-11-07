
package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.R
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    /**
     * En la clase ReplyAppTest, crea una regla de prueba con createAndroidComposeRule y pasa ComponentActivity
     * como el parámetro de tipo. Se usa ComponentActivity para acceder a una actividad vacía en lugar de a MainActivity.
     */

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

/*Para diferenciar los elementos de navegación de las pantallas, agrega un elemento testTag en el elemento
 ReplyBottomNavigationBar componible.
 */

    /*Usa las anotaciones en las funciones de prueba para pruebas compactas colocando la anotación TestCompactWidth
    después de la anotación de prueba para una prueba compacta en ReplyAppTest y ReplyAppStateRestorationTest.
     */

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {

        composeTestRule.setContent {
            /*En la clase ReplyAppTest, crea una función de prueba a fin de probar una pantalla de tamaño compacto.
     Configura el contenido de composeTestRule con el elemento componible ReplyApp y pasa WindowWidthSizeClass.Compact como el argumento windowSize.
     */
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }
        /*Confirma que el elemento de navegación inferior existe con la etiqueta de prueba. Llama a la función de
        extensión onNodeWithTagForStringId en composeTestRule, pasa la string de navegación inferior y llama al método
         assertExists().
         */
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_bottom
        ).assertExists()
    }


   // Agrega una prueba para verificar que exista un elemento de riel de navegación en las pantallas medianas.
    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail() {

        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }

        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_rail
        ).assertExists()
    }

    //Agrega una prueba para verificar que exista un elemento de panel lateral de navegación en las pantallas expandidas.

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer() {

        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }

        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_drawer
        ).assertExists()
    }
}