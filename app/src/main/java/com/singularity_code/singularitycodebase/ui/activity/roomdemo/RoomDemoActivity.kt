package com.singularity_code.singularitycodebase.ui.activity.roomdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.singularity_code.codebase.util.db
import com.singularity_code.singularitycodebase.data.db.Database
import com.singularity_code.singularitycodebase.data.model.User
import com.singularity_code.singularitycodebase.util.theme.SingularityCodebaseTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoomDemoActivity : ComponentActivity() {

//    private val userDB by UserDb.db()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SingularityCodebaseTheme {
                // A surface container using the 'background' color from the theme
                Screen()
            }
        }
    }
}

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

@Composable
fun ColumnScope.UserList() {

    val db by db<Database>()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.AddUserForm() {
    val db by db<Database>()
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

@Preview(showSystemUi = false)
@Composable
fun Preview() {
    Screen()
}