
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.david.squid_mail.screen.CalendarScreen
import com.david.squid_mail.screen.InboxScreen
import com.david.squid_mail.screen.LoginScreen
import com.david.squid_mail.screen.LoginViewModel
import com.david.squid_mail.screen.RegistrationScreen
import com.david.squid_mail.screen.RegistrationViewModel
import com.david.squid_mail.screen.WelcomeScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
   NavHost(navController = navController, startDestination = "inbox"){
      composable("welcome"){
         WelcomeScreen(navController)
      }
      composable("register"){
         RegistrationScreen(viewModel = RegistrationViewModel(), navController)
      }
      composable("login"){
         LoginScreen(viewModel = LoginViewModel(), navController)
      }
      composable("inbox"){
         InboxScreen()
      }
      composable("calendar"){
         CalendarScreen()
      }
      
   }
}