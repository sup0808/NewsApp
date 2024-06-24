import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField


import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.demo.newsapp.presentation.login.LoginViewModel
import com.demo.newsapp.presentation.login.state.LoginEvent



@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val loginstate = loginViewModel.loginState.value

    when(loginstate.status){
        200 ->{
            LaunchedEffect(key1 = Unit) {
                navController.navigate(MainDestination.HOME_ROUTE)
            }

        }
        404 -> {

        }
    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp,
            backgroundColor = Color.Cyan,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserNameField(
                    value = loginViewModel.userState.value.emailId,
                    onChange = {
                        loginViewModel.onAction(LoginEvent.EmailChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isError = loginViewModel.errorState.value.emailStatus

                )
                PasswordTextField(
                    value = loginViewModel.userState.value.password,
                    onChange = {
                      loginViewModel.onAction(LoginEvent.PasswordChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isError = loginViewModel.errorState.value.emailStatus
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                              loginViewModel.validateUserLogin()
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .width(100.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(10.dp))

                LabeledCheckBox("Remember me", false, "Forgot password?") {

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
    placeholder: String = "Enter the Email id",
    isError: Boolean ,
    error: String = "Please enter valid email id"

) {
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.AccountCircle, contentDescription = "", tint = Color.Blue
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
        placeholder = { Text(placeholder, style = TextStyle(color = Color.Blue)) },
        label = { Text(label, style = TextStyle(color = Color.Blue)) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue, textColor = Color.Blue
        ),
        isError = isError
        )
    if (isError) {
        Text(
            text = error,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp, top = 0.dp)
        )
    }

}


@Composable
fun PasswordTextField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter the password",
    isError: Boolean ,
    error: String = "Please enter valid password"
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val leadingIcon = @Composable {
        Icon(Icons.Default.Lock, contentDescription = "", tint = Color.Blue)
    }

    val trailingIcon = @Composable {
        IconButton(onClick = {
            isPasswordVisible = !isPasswordVisible
        }) {
            Icon(
                if (isPasswordVisible) Icons.Default.Check else Icons.Default.Close,
                contentDescription = "",
                tint = Color.LightGray
            )
        }

    }


    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
        ),
        /* keyboardActions = KeyboardActions(
             onNext = { focusManager.moveFocus(FocusDirection.Down) }
         ),*/
        placeholder = { Text(placeholder, style = TextStyle(color = Color.Blue)) },
        label = { Text(label, style = TextStyle(color = Color.Blue)) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue, textColor = Color.Blue
        ),
        isError = isError
    )
    if (isError) {
        Text(
            text = error,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp, top = 0.dp)
        )
    }

}

@Composable
fun LabeledCheckBox(
    label: String,
    isChecked: Boolean,
    forgotPass: String,
    onCheckChanged: () -> Unit

) {

    Row(
        modifier = Modifier.clickable {
            onCheckChanged()
        }) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = label, style = TextStyle(color = Color.Blue))
        Spacer(Modifier.weight(1f))
        Text(text = forgotPass, style = TextStyle(color = Color.Blue))
    }

}
