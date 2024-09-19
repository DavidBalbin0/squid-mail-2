import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.david.squid_mail.model.FolderType
import com.david.squid_mail.screen.CalendarScreen
import com.david.squid_mail.screen.EmailCompositionScreen
import com.david.squid_mail.screen.EmailCompositionViewModel
import com.david.squid_mail.screen.EmailReadScreen
import com.david.squid_mail.screen.EmailReadViewModel
import com.david.squid_mail.screen.FolderScreen
import com.david.squid_mail.screen.FolderViewModel
import com.david.squid_mail.screen.EmailScreen
import com.david.squid_mail.screen.EmailViewModel
import com.david.squid_mail.screen.LoginScreen
import com.david.squid_mail.screen.LoginViewModel
import com.david.squid_mail.screen.RegistrationScreen
import com.david.squid_mail.screen.RegistrationViewModel
import com.david.squid_mail.screen.SettingsScreen
import com.david.squid_mail.screen.WelcomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationComponent(navController: NavHostController) {
    val context = LocalContext.current.applicationContext
    NavHost(navController = navController, startDestination = "calendar") {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("register") {
            RegistrationScreen(viewModel = RegistrationViewModel(), navController)
        }
        composable("login") {
            LoginScreen(viewModel = LoginViewModel(), navController)
        }

        composable("email-details") {
            EmailReadScreen(viewModel = EmailReadViewModel(), navController)
        }
        composable("email-composition") {
            EmailCompositionScreen(
                viewModel = EmailCompositionViewModel(context),
                navController
            )
        }
        composable("calendar") {
            CalendarScreen()
        }
        composable("folder") {
            FolderScreen(folderViewModel = FolderViewModel(context), navController)
        }

        composable("inbox") {
            EmailScreen(viewModel = EmailViewModel(context), navController)
        }

        composable("sent") {
            EmailScreen(viewModel = EmailViewModel(context, FolderType.SENT), navController)
        }
        composable("drafts") {
            EmailScreen(viewModel = EmailViewModel(context, FolderType.DRAFTS), navController)
        }

        composable("trash") {
            EmailScreen(viewModel = EmailViewModel(context, FolderType.TRASH), navController)
        }

        composable("archived") {
            EmailScreen(viewModel = EmailViewModel(context, FolderType.ARCHIVED), navController)
        }
        composable("favorites") {
            EmailScreen(viewModel = EmailViewModel(context, FolderType.FAVORITES), navController)
        }
        composable("spam") {
            EmailScreen(viewModel = EmailViewModel(context, FolderType.SPAM), navController)
        }

        composable("calendar") {
            CalendarScreen()
        }

        composable("settings") {
            SettingsScreen(navController)
        }

        composable("dynamic/{folderId}") { backStackEntry ->
            val folderId = backStackEntry.arguments?.getString("folderId")?.toLongOrNull()
            Log.i("NavigationComponent", "folderId: $folderId in navigation component")
            EmailScreen(viewModel = EmailViewModel(context, FolderType.OTHER, folderId), navController)
        }
    }
}

