package com.example.aissms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class OrderSnapshot extends AppCompatActivity {
    TextView tv,amount,cost;
    ImageView add,remove;
    String ITEM;
    Button pay;
    static Integer COUNT=0;
    private static final String UPI_ID = "7507457622@ybl";
    FirebaseFirestore db;
    CollectionReference cr;
    static Map<String, Integer> m;
CollectionReference cr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_snapshot);
        db=FirebaseFirestore.getInstance();
        createMap();
        cr=db.collection("PaymentDetails");
        tv=findViewById(R.id.ordername);
        cost=findViewById(R.id.cost);
        amount=findViewById(R.id.amount);
        add=findViewById(R.id.add);
        remove=findViewById(R.id.remove);
        pay=findViewById(R.id.addtocart);
        cr2=db.collection("Users");

        Intent intent=getIntent();
       final String s= intent.getStringExtra("name");

        tv.setText(s);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] payment = {"Paytm"
                        ,"UPI"
                       ,"Cash"
                };
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                                        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(OrderSnapshot.this);
                                        alertDialogBuilder2.setTitle("Choose a payment method:")
                                                .setItems(payment, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        ProgressDialog progressDialog=new ProgressDialog(OrderSnapshot.this);
                                                        progressDialog.setMessage("Loading...");
                                                        progressDialog.show();
                                                        switch (i) {
                                                            case 0:
                                                                    additem1(s);
                                                                    ITEM=s;
                                                                    progressDialog.dismiss();
                                                                    Intent intent=new Intent(OrderSnapshot.this,checksum.class);
                                                                    intent.putExtra("orderid", uuid_gen());//uuid
                                                                    intent.putExtra("custid", getSaltString());//rnd
                                                                    intent.putExtra("final_amount", 1);//amount change here
//                    startActivity(new Intent(OrderSnapshot.this,checksum.class));
                                                                    startActivityForResult(intent,1);

//                                                                Paytm_transaction();
                                                                break;
                                                            case 1:
                                                                additem1(s);
                                                                ITEM=s;
                                                                progressDialog.dismiss();
                                                                UPI_transaction();
                                                                break;
                                                        case 2:
                                                            additem1(s);
                                                            ITEM=s;
                                                            progressDialog.dismiss();
                                                            Cash_transaction();
                                                            break;
                                                        }
                                                    }
                                                })
                                                .setCancelable(true)
                                                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                    @Override
                                                    public void onCancel(DialogInterface dialogInterface) {
//                                                        register.setVisibility(View.VISIBLE);
//                                                        register1.setVisibility(View.GONE);
                                                        Toast.makeText(OrderSnapshot.this, "Cancelled by user.", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .show();
                                    }


                }, 3000);
            }
        });
    }


    public void removeitem(View view)
    {
        int count= Integer.parseInt(amount.getText().toString());
        if(count>1)
        {
            count--;
            COUNT=count;
           del(ITEM);
            amount.setText(String.valueOf(count));
            cost.setText("Cost: Rs. "+String.valueOf(count*100));
        }
    }
    public void additem(View view)
    {
        int count= Integer.parseInt(amount.getText().toString());
        count++;
        amount.setText(String.valueOf(count));
        COUNT=count;
        cost.setText("Cost: Rs. "+String.valueOf(count*100));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                if(data!=null)
                {
                    if(data.hasExtra("TXN_RESPONSE"))
                    {
                        if(data.getStringExtra("TXN_RESPONSE").equals("TXN_SUCCESS"))
                        {
                            User user=new User((Integer.parseInt(amount.getText().toString())),getSaltString(),viewcart(),"");
                            cr.add(user)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(OrderSnapshot.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                                                String message = "";
                                                Resources res = getResources();
//                                                String dontreply = String.format(res.getString(R.string.donotreply));
                                                String email1 ="tanushmaddiwar@gmail.com";//abhi ke liye temp
                                                String subject = "Food Order";
                                                message="Your Transaction is successful(Paytm).";
                                                SendMail sm = new SendMail(OrderSnapshot.this, email1, subject, message);
                                                //Executing sendmail to send email
                                                sm.execute();

                                                message = "";
                                                Resources res1 = getResources();
//                                                String dontreply = String.format(res.getString(R.string.donotreply));
                                                String email11 ="gauravdesh26@gmail.com";//abhi ke liye temp
                                                String subject1 = "New Food Order";
                                                message="New Order via PayTM";
                                                SendMail sm1 = new SendMail(OrderSnapshot.this, email11, subject1, message);
                                                //Executing sendmail to send email
                                                sm.execute();
                                                startActivity(new Intent(OrderSnapshot.this,MainActivity.class));
                                            }
                                            else{
                                                Toast.makeText(OrderSnapshot.this, "Some Error Occured Try Again", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
        }
    }
    protected static String getSaltString() {
        String SALTCHARS = "BDEFHIJ736KMNPQRSTUVWYZ123456A78908H70546S32";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    String uuid_gen()
    {
        UUID uuid=UUID.randomUUID();
        String orderID=uuid.toString();
        return orderID;
    }

    public void Cash_transaction()
    {

        User user=new User(Integer.parseInt(amount.getText().toString()),getSaltString(),viewcart(), FirebaseApp.getInstance().getName());
        cr.add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(OrderSnapshot.this, "Order Placed.", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(OrderSnapshot.this,MainActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OrderSnapshot.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

//                Toast.makeText(OrderSnapshot.this, "user registered", Toast.LENGTH_SHORT).show();
        String message = "";
        Resources res = getResources();
//                                                String dontreply = String.format(res.getString(R.string.donotreply));
        String email1 ="tanushmaddiwar@gmail.com";//abhi ke liye temp
        String subject = "Food Order";
        message="Your Order has been placed\n\nKindly Pay "+cost.getText().toString()+" in Cash at the counter while receiving the order.";
        SendMail sm = new SendMail(OrderSnapshot.this, email1, subject, message);
        //Executing sendmail to send email
        sm.execute();

        message = "";
        Resources res1 = getResources();
//                                                String dontreply = String.format(res.getString(R.string.donotreply));
        String email11 ="gauravdesh26@gmail.com";//abhi ke liye temp
        String subject1 = "New Food Order";
        message="New Order via Cash";
        SendMail sm1 = new SendMail(OrderSnapshot.this, email11, subject1, message);
        //Executing sendmail to send email
        sm1.execute();
        startActivity(new Intent(OrderSnapshot.this,MainActivity.class));

    }

    private String RefIDGen() {
        StringBuilder RefId = new StringBuilder();
        final int min = 1000;
        final int max = 9999;
        int i = 0;
        while (i < 3) {
            final int random = new Random().nextInt((max - min) + 1) + min;
            RefId.append(random);
            i++;
        }
        return RefId.toString();
    }
    private String IDGen() {
        int i = 0;
        StringBuilder Id = new StringBuilder();
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        Random r = new Random();
        Id.append(alphabet.charAt(r.nextInt(N)));
        final int min = 1000;
        final int max = 9999;
        while (i < 5) {
            final int random = new Random().nextInt((max - min) + 1) + min;
            Id.append(random);
            i++;
        }
        final int min1 = 10;
        final int max1 = 99;
        final int random1 = new Random().nextInt((max1 - min1) + 1) + min1;
        Id.append(random1);
        return Id.toString();
    }
    private void UPI_transaction() {
        String TransRefId = RefIDGen();
        String TransId = IDGen();
        //Create instance of EasyUpiPayment
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                .setPayeeVpa(UPI_ID)
                .setPayeeName("Gaurav Deshpande")
                .setTransactionId(TransId)
                .setTransactionRefId(TransRefId)
                .setDescription("Order Payment")
                .setAmount("1.00")
                .build();
        easyUpiPayment.setPaymentStatusListener(new PaymentStatusListener() {
            @Override
            public void onTransactionCompleted(TransactionDetails transactionDetails) {
                Log.d("TransactionDetails", transactionDetails.toString());
            }
            @Override
            public void onTransactionSuccess() {
//                finish();
//                payment_method="UPI";
//                addTodb();
                User user=new User(Integer.parseInt(amount.getText().toString()),getSaltString(),viewcart(),"");
                cr.add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(OrderSnapshot.this, "Payment successful.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(OrderSnapshot.this,MainActivity.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(OrderSnapshot.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                            }
                        });
//                Toast.makeText(OrderSnapshot.this, "user registered", Toast.LENGTH_SHORT).show();
                String message = "";
                Resources res = getResources();
//                                                String dontreply = String.format(res.getString(R.string.donotreply));
                String email1 ="tanushmaddiwar@gmail.com";//abhi ke liye temp
                String subject = "Food Order";
                message="Your Transaction is successful(UPI Payment).";
                SendMail sm = new SendMail(OrderSnapshot.this, email1, subject, message);
                //Executing sendmail to send email
                sm.execute();

                message = "";
                Resources res1 = getResources();
//                                                String dontreply = String.format(res.getString(R.string.donotreply));
                String email11 ="gauravdesh26@gmail.com";//abhi ke liye temp
                String subject1 = "New Food Order";
                message="New Order via UPI";
                SendMail sm1 = new SendMail(OrderSnapshot.this, email11, subject1, message);
                //Executing sendmail to send email
                sm1.execute();
//                startActivity(new Intent(OrderSnapshot.this,OrderSnapshot.class));
            }
            @Override
            public void onTransactionSubmitted() {
//                payment_method="UPI";
                Toast.makeText(OrderSnapshot.this, "Pending | Submitted", Toast.LENGTH_SHORT).show();
//                addTodb();
            }
            @Override
            public void onTransactionFailed() {
                Toast.makeText(OrderSnapshot.this, "Not registered, try again.", Toast.LENGTH_SHORT).show();
//                register.setVisibility(View.VISIBLE);
//                register1.setVisibility(View.GONE);
            }
            @Override
            public void onTransactionCancelled() {
                Toast.makeText(OrderSnapshot.this, "Cancelled by user.", Toast.LENGTH_SHORT).show();
//                register.setVisibility(View.VISIBLE);
//                register1.setVisibility(View.GONE);
            }
        });
        easyUpiPayment.startPayment();
    }
    public static void createMap()
    {
        m=new HashMap<String,Integer>();
        String b="B";
        for(int i=0;i<10;i++)
        {
            String a=b+String.valueOf(i+1);
            m.put(a,0);
        }
        b="P";
        for(int i=0;i<10;i++)
        {
            String a=b+String.valueOf(i+1);
            m.put(a,0);
        }
        b="D";
        for(int i=0;i<10;i++)
        {
            String a=b+String.valueOf(i+1);
            m.put(a,0);
        }
    }

    public static void additem1(String s) throws NullPointerException {

        if(m.get(s)==null)
            return;

        m.put(s, m.get(s) +COUNT);
    }

    public static String viewcart()
    {
        String s="";

        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            if(entry==null)
                return s;
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            String k=entry.getKey();
            int p=entry.getValue();
            if(p!=0)
            {
                s+=k;
                String z=" x";
                s+=z;
                s+=String.valueOf(p);
                s+="\n";
            }
        }
        return s;
    }



public static void del(String s) throws NullPointerException
        {
            if(m.get(s)==null)return ;
        int a=m.get(s);

        if(a-COUNT>=0)
        m.put(s,m.get(s)-COUNT);

        }

}
