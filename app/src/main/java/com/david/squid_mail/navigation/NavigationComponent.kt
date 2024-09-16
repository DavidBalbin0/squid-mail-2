
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.david.squid_mail.screen.CalendarScreen
import com.david.squid_mail.screen.EmailCompositionScreen
import com.david.squid_mail.screen.EmailCompositionViewModel
import com.david.squid_mail.screen.EmailReadScreen
import com.david.squid_mail.screen.EmailReadViewModel
import com.david.squid_mail.screen.InboxScreen
import com.david.squid_mail.screen.InboxViewModel
import com.david.squid_mail.screen.LoginScreen
import com.david.squid_mail.screen.LoginViewModel
import com.david.squid_mail.screen.PreviewEmailDetailScreen
import com.david.squid_mail.screen.RegistrationScreen
import com.david.squid_mail.screen.RegistrationViewModel
import com.david.squid_mail.screen.WelcomeScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
   NavHost(navController = navController, startDestination = "register"){
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
         InboxScreen(viewModel = InboxViewModel(), navController)
      }
      composable("email-details") {
         EmailReadScreen(viewModel = EmailReadViewModel(), navController)
      }
      composable("email-composition") {
         EmailCompositionScreen(viewModel = EmailCompositionViewModel(LocalContext.current), navController)
      }
      composable("calendar"){
         CalendarScreen()
      }
      
   }
}