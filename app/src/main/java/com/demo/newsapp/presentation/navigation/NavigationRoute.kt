import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.newsapp.presentation.home.HomeScreen

object MainDestination {
    const val SPLASH_ROUTE = "splash"
    const val HOME_ROUTE = "home"
    const val LOGIN_ROUTE = "login"

}

@Composable
fun NavigatorRoute() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainDestination.SPLASH_ROUTE){
        composable(route= MainDestination.SPLASH_ROUTE){
            SplashScreen(navController)
        }
        composable(route = MainDestination.LOGIN_ROUTE){
            LoginScreen(navController)
        }
        composable(route = MainDestination.HOME_ROUTE){
            HomeScreen(navController)
        }
    }
}