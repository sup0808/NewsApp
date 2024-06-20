import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun LoginScreen(navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp,

            backgroundColor = Color.Gray,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserNameField(
                    value = "username", onChange = { }, modifier = Modifier.fillMaxWidth()
                )
                UserNameField(
                    value = "username", onChange = { }, modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = {}, modifier = Modifier
                    .wrapContentWidth()
                    .width(100.dp), shape = RoundedCornerShape(5.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
                    Text(text = "Login")
                }

            }

        }
    }
}


@Composable
fun UserNameField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Username",
    placeholder: String = "Enter the username"
) {
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.AccountCircle, contentDescription = "", tint = Color.LightGray
        )
    }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
        ),
        /* keyboardActions = KeyboardActions(
             onNext = { focusManager.moveFocus(FocusDirection.Down) }
         ),*/
        placeholder = { Text(placeholder, style = TextStyle(color = Color.LightGray)) },
        label = { Text(label, style = TextStyle(color = Color.DarkGray)) },

        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray, textColor = Color.DarkGray
        ),
    )

}