package com.your.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
class Главная : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityГлавная)
        Glide.with(this).load("https://i.imgur.com/1tMFzp8.png").into(findViewById(R.id.rgbg3eucl6p))
		Glide.with(this).load("https://i.imgur.com/1tMFzp8.png").into(findViewById(R.id.rd8a2afuslxe))
		Glide.with(this).load("https://i.imgur.com/1tMFzp8.png").into(findViewById(R.id.ry23ohfc0id))
		Glide.with(this).load("https://i.imgur.com/1tMFzp8.png").into(findViewById(R.id.ruldpc3ftd9q))
		Glide.with(this).load("https://i.imgur.com/1tMFzp8.png").into(findViewById(R.id.r06bl4t29vmjx))
		Glide.with(this).load("https://i.imgur.com/1tMFzp8.png").into(findViewById(R.id.rrmw56j54yyg))
    }
}