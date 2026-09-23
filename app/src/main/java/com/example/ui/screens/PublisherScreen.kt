package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.PublisherFormState
import com.example.ui.theme.NewsBreakingRed
import com.example.ui.theme.NewsGoldAccent

@Composable
fun PublisherScreen(
    formState: PublisherFormState,
    categories: List<String>,
    onUpdateForm: (
        title: String?,
        subtitle: String?,
        content: String?,
        category: String?,
        author: String?,
        imageUrl: String?,
        isBreaking: Boolean?,
        source: String?
    ) -> Unit,
    onSubmit: () -> Unit,
    onClearSuccess: () -> Unit
) {
    val presetImages = listOf(
        Pair("US Capitol", "https://images.unsplash.com/photo-1541872703-74c5e44368f9?w=800&q=80"),
        Pair("Wall Street", "https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?w=800&q=80"),
        Pair("Silicon Valley", "https://images.unsplash.com/photo-1518770660439-4636190af475?w=800&q=80"),
        Pair("NASA / Space", "https://images.unsplash.com/photo-1446776811953-b23d57bd21aa?w=800&q=80"),
        Pair("Press Conference", "https://images.unsplash.com/photo-1585829365295-ab7cd400c167?w=800&q=80"),
        Pair("Sports Arena", "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=800&q=80")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Header
        Text(
            text = "US News Publisher Studio",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Write and publish articles with real-time SEO & AdSense score validation.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Success / Error Banner
        formState.submitSuccessMessage?.let { msg ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDCFCE7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClearSuccess() }
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF166534)
                    )
                    Text(
                        text = msg,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = Color(0xFF166534)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        formState.errorMessage?.let { err ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ErrorOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = err,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Live SEO & AdSense Readiness Meter
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "SEO & AdSense Quality Score",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Text(
                        text = "${formState.seoScore}%",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                        color = if (formState.seoScore >= 70) Color(0xFF16A34A) else NewsGoldAccent
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { formState.seoScore / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = if (formState.seoScore >= 70) Color(0xFF16A34A) else NewsGoldAccent
                )

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Words: ${formState.wordCount} (Goal: 150+)",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (formState.wordCount >= 150) Color(0xFF16A34A) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Estimated Read: ${formState.estimatedReadMinutes} min",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title Input
        OutlinedTextField(
            value = formState.title,
            onValueChange = { onUpdateForm(it, null, null, null, null, null, null, null) },
            label = { Text("Article Headline *") },
            placeholder = { Text("e.g., U.S. Senate Passes Historic Economic Package") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_article_title"),
            singleLine = false,
            maxLines = 3,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Subtitle / Deck
        OutlinedTextField(
            value = formState.subtitle,
            onValueChange = { onUpdateForm(null, it, null, null, null, null, null, null) },
            label = { Text("Subtitle / Lead Summary") },
            placeholder = { Text("Brief one-sentence summary for SEO meta description") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_article_subtitle"),
            singleLine = false,
            maxLines = 2,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Category Selector
        Text(
            text = "Select Category",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.filter { it != "All" }.forEach { cat ->
                FilterChip(
                    selected = formState.category == cat,
                    onClick = { onUpdateForm(null, null, null, cat, null, null, null, null) },
                    label = { Text(cat) },
                    modifier = Modifier.testTag("chip_cat_$cat")
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Author & Source
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = formState.author,
                onValueChange = { onUpdateForm(null, null, null, null, it, null, null, null) },
                label = { Text("Reporter / Author") },
                modifier = Modifier
                    .weight(1f)
                    .testTag("input_article_author"),
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                value = formState.source,
                onValueChange = { onUpdateForm(null, null, null, null, null, null, null, it) },
                label = { Text("News Bureau / Source") },
                modifier = Modifier
                    .weight(1f)
                    .testTag("input_article_source"),
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Breaking News Toggle
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = if (formState.isBreaking) NewsBreakingRed.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FlashOn,
                        contentDescription = null,
                        tint = if (formState.isBreaking) NewsBreakingRed else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Column {
                        Text(
                            text = "Mark as Breaking News",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Feature at the top of the feed with alert banner",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Switch(
                    checked = formState.isBreaking,
                    onCheckedChange = { onUpdateForm(null, null, null, null, null, null, it, null) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = NewsBreakingRed,
                        checkedTrackColor = NewsBreakingRed.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.testTag("toggle_breaking_switch")
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Image Presets
        Text(
            text = "Featured Image Presets",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            presetImages.forEach { (name, url) ->
                FilterChip(
                    selected = formState.imageUrl == url,
                    onClick = { onUpdateForm(null, null, null, null, null, url, null, null) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Image, contentDescription = null, modifier = Modifier.size(16.dp))
                    },
                    label = { Text(name) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = formState.imageUrl,
            onValueChange = { onUpdateForm(null, null, null, null, null, it, null, null) },
            label = { Text("Image URL") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_article_image_url"),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Content Body
        OutlinedTextField(
            value = formState.content,
            onValueChange = { onUpdateForm(null, null, it, null, null, null, null, null) },
            label = { Text("Article Content Body *") },
            placeholder = { Text("Write your full reporting here. Include facts, quotes, and context...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .testTag("input_article_content"),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Publish Button
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("btn_publish_article"),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Publish, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Publish to US News Feed",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
