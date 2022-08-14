package com.sudo.androidd20

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: MyBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiver = MyBroadcastReceiver()
        /*
            IntentFilter rất hữu ích để xác định ứng dụng nào muốn nhận ý định nào,
            vì ở đây chúng tôi muốn phản hồi việc thay đổi chế độ trên máy bay
             */
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            /*
            đăng ký tham số receiver it được truyền vào hàm registerReceiver () là IntentFilter mà chúng ta vừa tạo
             */
            registerReceiver(receiver,it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}