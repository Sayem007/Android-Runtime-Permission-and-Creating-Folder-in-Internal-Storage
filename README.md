# Runtime Storage Permission and Creating Folder/Sub-Folder

A sample android application to check Runtime (Storage) Permission and Creating Folder or Sub-folder in Internal Storage.

## Preview

![Pr4](https://i.imgflip.com/283op7.gif)

## Installation & Usage
* Edit your Manifest file with this

     
         <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />


* Checking if the permission is not already granted, then request for permission to write externl storage. 


        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST);
            }
        }

here the requestCode is 'MY_PERMISSIONS_REQUEST'. The value is 1.


* Now showing toast when permission is granted or denied in RequestPermissionResult

    
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(MainActivity.this, "Permission granted.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        }


* Now on Button Click Listener, we will call Android Environment in order to specify new forder or sub-folder according to our choice.

       File folder = new File(Environment.getExternalStorageDirectory() + "/Our folder/Our sub-folder");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            // Do your own thing on success
        } else {
            // Do your own thing else on failure 
        }

