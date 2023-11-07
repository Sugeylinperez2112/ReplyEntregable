
package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.R
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    /**
     * En la clase ReplyAppStateRestorationTest, crea una regla de prueba con createAndroidComposeRule y
     * pasa ComponentActivity como el parámetro de tipo.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        // Configurar ventana compacta
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }


        //Se muestra el tercer correo electrónico dado
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        // Haz clic en el asunto del correo electrónico para navegar a su pantalla de detalles. Usa el método performClick() para la navegación.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        // Verifique que muestra la pantalla detallada del correo electrónico correcto
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()

        // Simular un cambio de configuración
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verifique que todavía muestra la pantalla detallada para el mismo correo electrónico
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()
    }
    /*Crea una función de prueba que verifique que un correo electrónico aún aparezca seleccionado en la pantalla compacta
     después de un cambio de configuración.
     */

  /* Configura stateRestorationTester pasando composeTestRule como argumento a StateRestorationTester.
Usa setContent() con el elemento componible ReplyApp y pasa el elemento WindowWidthSizeClass.Compact como el argumento windowSize.
   */
    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        // Configurar ventana expandida
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }

        // Se muestra el tercer correo electrónico dado
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        // Seleccione el tercer correo electrónico
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()


      //Verifique que el tercer correo electrónico se muestre en la pantalla de detalles
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)))
            )

        // Simular un cambio de configuración
        stateRestorationTester.emulateSavedInstanceStateRestore()


      //Verifique que el tercer correo electrónico todavía se muestre en la pantalla de detalles
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)))
            )
    }
}