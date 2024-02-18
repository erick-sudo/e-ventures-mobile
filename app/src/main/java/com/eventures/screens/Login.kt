package com.eventures.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eventures.R
import com.eventures.ui.UnderlinedNavRail
import com.eventures.viewmodels.LoginViewModel
import kotlinx.coroutines.launch


data class LoginFormData(
    var email: String = "",
    var phone: String = "",
    var password: String = "",
    var pin: String = ""
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Login(
    loginViewModel: LoginViewModel
) {

    val formData by remember {
        mutableStateOf(LoginFormData())
    }

    val coroutineScope = rememberCoroutineScope()

    val loginOptionsPagerState = rememberPagerState(
        initialPage = 2,
        pageCount = { 3 }
    )

    val loginTabs: List<@Composable (MutableIntState) -> Unit> = listOf("Email", "Phone", "Pin")
        .mapIndexed { idx, label -> { state ->
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = label,
                color = if (state.intValue == idx) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
            )
        } }

    LazyColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.land3),
                    contentDescription = "Login Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            alpha = 0.7f,
                            brush = Brush.linearGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    Color.Transparent
                                )
                            )
                        ),
                ) {
                    ExtendedFloatingActionButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(15.dp)
                    ) {
                        Text(text = "Register Now")
                    }
                }
            }

            UnderlinedNavRail(
                modifier = Modifier
                    .padding(5.dp),
                onChange = { coroutineScope.launch { loginOptionsPagerState.animateScrollToPage(it) } },
                items = loginTabs
            )

            HorizontalPager(
                modifier = Modifier
                    .height(200.dp),
                state = loginOptionsPagerState
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when(page) {
                        0 -> {
                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                modifier = Modifier,
                                value = formData.email,
                                onValueChange = { /* */ },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email
                                ),
                                singleLine = true,
                                label = { Text("Enter your email") },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email Address")
                                }
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                modifier = Modifier,
                                value = formData.password,
                                onValueChange = { /* */ },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password
                                ),
                                singleLine = true,
                                label = { Text("Password") },
                                trailingIcon = {
                                    Icon(imageVector = ImageVector.vectorResource(R.drawable.outline_remove_red_eye_24), contentDescription = "Password")
                                }
                            )
                        }
                        1 -> {
                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                modifier = Modifier,
                                value = formData.email,
                                onValueChange = { /* */ },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                singleLine = true,
                                label = { Text("Enter your phone number") },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Outlined.Call, contentDescription = "Email Address")
                                }
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                modifier = Modifier,
                                value = formData.password,
                                onValueChange = { /* */ },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password
                                ),
                                singleLine = true,
                                label = { Text("Password") },
                                trailingIcon = {
                                    Icon(imageVector = ImageVector.vectorResource(R.drawable.outline_remove_red_eye_24), contentDescription = "Password")
                                }
                            )
                        }
                        else -> {
                            Text(text = "Input Pin")

                            Spacer(modifier = Modifier.height(20.dp))

                            Row {
                                listOf(0, 1, 2, 3).map {
                                    Spacer(modifier = Modifier
                                        .width(20.dp))
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(50.dp),
                                        textStyle = TextStyle(
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number
                                        ),
                                        singleLine = true,
                                        value = "$it", onValueChange = {}
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            
            OutlinedButton(
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 60.dp),
                onClick = { loginViewModel.changeLoginStatus(1) }
            ) {
                Text(text = "Submit")
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                modifier = Modifier,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 60.dp),
                onClick = { /*TODO*/ },
            ) {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_fingerprint_24 ), contentDescription = "Fingerprint icon")
                Spacer(modifier = Modifier
                    .width(20.dp))
                Text(
                    text = "Login with biometrics",

                )
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun LoginPreview() {

}