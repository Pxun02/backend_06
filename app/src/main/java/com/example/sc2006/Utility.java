package com.example.sc2006;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class Utility {
    public static Object[] fbRead(String table, int i){
        DatabaseReference mDatabase = FirebaseDatabase
            .getInstance("https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference();
        Query mQueryRef = mDatabase.child(table);
        Object objPtr[] = new Object[i];

        mQueryRef.addChildEventListener(new ChildEventListner(){
            @Override
            public void onChildAdded(DataSnapshot snapshot){
                for(int j=0;j<i;j++)
                    objPtr[j] = snapshot.child(Integer.toString(j)).getValue(Object.class);
            }
        });
        return objPtr;
    }

    public static void fbWrite(Object obj, String table, int id){
        Map<String, Object> objs = new HashMap<>();
        objs.put(Integer.toString(id), obj);
        String URL = "https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/";
        DatabaseReference mDatabase = FirebaseDatabase.getInstance(URL).getReference(table);
        mDatabase.setValue(objs);
    }
/*
    public JSONObject storageRead(String filepath){
        StorageReference storageRef = storage.getReference();

        // Create a reference with an initial file path and name
        StorageReference pathReference = storageRef.child(filepath);

        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        
    }
    
    public void storageWrite(Object obj, String filepath){
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://my-custom-bucket");

        StorageReference storageRef = storage.getReference();

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);

        Uri file = Uri.fromFile(new File(filepath));
        StorageReference fileRef = storageRef.child("images/"+file.getLastPathSegment());
        uploadTask = fileRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }
 */
}
