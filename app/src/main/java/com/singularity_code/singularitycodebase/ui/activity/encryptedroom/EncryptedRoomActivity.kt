package com.singularity_code.singularitycodebase.ui.activity.encryptedroom

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.singularity_code.codebase.util.Singularity
import com.singularity_code.codebase.util.system.getSimCardInformation
import com.singularity_code.codebase.util.system.hasPermissions
import com.singularity_code.codebase.util.io.room.encryptedDb
import com.singularity_code.singularitycodebase.data.db.MyEncryptedDatabase
import com.singularity_code.singularitycodebase.data.model.User
import com.singularity_code.singularitycodebase.ui.activity.encryptedroom.EncryptedRoom.Companion.DB_PASSWORD
import com.singularity_code.singularitycodebase.util.theme.SingularityCodebaseTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EncryptedRoom : ComponentActivity() {

    companion object {
        val DB_PASSWORD
            @RequiresApi(Build.VERSION_CODES.Q)
            get() = Singularity.application.getSimCardInformation
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasPermissions(listOf(Manifest.permission.READ_PHONE_STATE))) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                100
            )
        }

        setContent {
            SingularityCodebaseTheme {
                Screen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Screen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            UserList()
            AddUserForm()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ColumnScope.UserList() {

    /**
     * Note that im giving you example as simple as possible so it is less too you to get trough the mechanism;
     * There fore the architecture might not be satisfying.
     * But of course for the real world execution, i recommend you to use prefer architecture such Clean Architecture or such.
     */
    val db by encryptedDb<MyEncryptedDatabase>(DB_PASSWORD)

    val userList = remember {
        mutableStateOf(
            (1..10).map { User(it, "adn", "asdn") }
        )
    }.apply {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(1000)
                value = db.userDao().getAll()
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        items(userList.value) { user ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        16.dp,
                        8.dp,
                        16.dp,
                        8.dp
                    )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "UID : ${user.uid}")
                    Text(text = "First Name : ${user.firstName}")
                    Text(text = "Last Name : ${user.lastName}")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.AddUserForm() {

    /**
     * Note that im giving you example as simple as possible so it is less too you to get trough the mechanism;
     * There fore the architecture might not be satisfying.
     * But of course for the real world execution, i recommend you to use prefer architecture such Clean Architecture or such.
     */
    val db by encryptedDb<MyEncryptedDatabase>(DB_PASSWORD)

    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 50.dp),
        shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TextField(
                label = { Text(text = "First Name") },
                value = firstName.value,
                onValueChange = {
                    firstName.value = it
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                label = { Text(text = "Last Name") },
                value = lastName.value,
                onValueChange = {
                    lastName.value = it
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.userDao().insertAll(
                            User(
                                uid = (Math.random() * 10000).toInt(),
                                firstName = firstName.value,
                                lastName = lastName.value
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add User")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showSystemUi = false)
@Composable
fun Preview() {
    Screen()
}