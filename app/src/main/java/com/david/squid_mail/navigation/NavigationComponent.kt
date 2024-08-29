
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.david.squid_mail.screen.RegistrationScreen
import com.david.squid_mail.screen.WelcomeScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
   NavHost(navController = navController, startDestination = "welcome"){
      composable("welcome"){
         WelcomeScreen(navController)
      }
      composable("register"){
         RegistrationScreen(navController)
      }
      
   }
}