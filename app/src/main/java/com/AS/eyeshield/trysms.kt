//package com.AS.eyeshield
//
//class trysms {
//    init {
//        var onClick: Unit
//        View
//        view
//        run {
//            val smsstring = "hello"
//            val ownercontact1 = "1234567890"
//            SendSms(ownercontact1, smsstring)
//        }
//    }
//
//    fun SendSms(ownerNo: String?, smsstring: String?) {
//        if (ContextCompat.checkSelfPermission(
//                this@QueryActivity,
//                Manifest.permission.SEND_SMS
//            ) === PackageManager.PERMISSION_GRANTED
//        );
//        ContextCompat.checkSelfPermission(
//            this@QueryActivity,
//            Manifest.permission.READ_PHONE_STATE
//        ) === PackageManager.PERMISSION_GRANTED
//        run {
//            SmsManager.getSmsManagerForSubscriptionId(SmsManager.getDefaultSmsSubscriptionId())
//            sm.sendTextMessage(ownerNo, null, smsstring, null, null)
//            Toast.makeText(
//                getApplicationContext(),
//                "Your request has been sent through SMS successfully...",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//        run {
//            Toast.makeText(
//                getApplicationContext(),
//                "Please Allow the App to send SMS and then Place Request Again",
//                Toast.LENGTH_LONG
//            ).show()
//            ActivityCompat.requestPermissions(
//                this@QueryActivity, arrayOf<String>(Manifest.permission.SEND_SMS),
//                1
//            )
//        }
//    }
//
//
//
//
//
//
//    fun sendMessage(view: View) {
//        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            myMessage()
//        } else {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),
//                permissionRequest)
//        }
//    }
//    private fun myMessage() {
//        val myNumber: String = editTextNumber.text.toString().trim()
//        val myMsg: String = editTextMessage.text.toString().trim()
//        if (myNumber == "" || myMsg == "") {
//            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
//        } else {
//            if (TextUtils.isDigitsOnly(myNumber)) {
//                val smsManager: SmsManager = SmsManager.getDefault()
//                smsManager.sendTextMessage(myNumber, null, myMsg, null, null)
//                Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Please enter the correct number", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults:
//    IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == permissionRequest) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                myMessage();
//            } else {
//                Toast.makeText(this, "You don't have required permission to send a message",
//                    Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//}
//
//
//
