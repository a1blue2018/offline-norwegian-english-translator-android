package com.translator.norwegianenglish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.translator.norwegianenglish.ui.theme.TranslatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TranslatorApp()
                }
            }
        }
    }
}

@Composable
fun TranslatorApp() {
    var sourceText by remember { mutableStateOf("") }
    var translatedText by remember { mutableStateOf("") }
    var isNorwegianToEnglish by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Norwegian-English Translator",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Language Toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { isNorwegianToEnglish = true },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNorwegianToEnglish) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                )
            ) {
                Text("NO → EN")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { isNorwegianToEnglish = false },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isNorwegianToEnglish) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                )
            ) {
                Text("EN → NO")
            }
        }

        // Source Text Input
        Text(
            text = if (isNorwegianToEnglish) "Norwegian" else "English",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = sourceText,
            onValueChange = { sourceText = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = { Text("Enter text to translate...") },
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Translate Button
        Button(
            onClick = {
                translatedText = TranslatorEngine.translate(sourceText, isNorwegianToEnglish)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Translate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Translated Text Output
        Text(
            text = if (isNorwegianToEnglish) "English" else "Norwegian",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Text(
                text = translatedText,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Copy Button
        Button(
            onClick = {
                // Copy to clipboard logic here
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Copy Translation")
        }
    }
}
