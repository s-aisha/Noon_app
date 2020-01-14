package as.com.noonlibrary;

import android.app.Fragment;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.io.File;
import java.io.RandomAccessFile;



public class BooksId extends Fragment {

    private EditText  bid;
    private Button find;
    private String borrowData = "BorrowData";
    private TextView list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.booksid,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);


        bid = (EditText) customView.findViewById(R.id.bookidbip);
        find = (Button) customView.findViewById(R.id.findstudents);
        list = (TextView) customView.findViewById(R.id.liststudents);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String lbid = bid.getText().toString();

                if(lbid.length()>0){

                    getAllData(Integer.parseInt(lbid));
                }

            }
        });


    }

    private void getAllData( int bid) {

        try{


            File f1 = new File(getActivity().getFilesDir(), bid+borrowData+".txt");
            RandomAccessFile raf1 = new RandomAccessFile(f1,"rw");
            String csid = getNo(raf1);


            if(csid.length() >0){
                list.setText(csid);
            }
            else{
                TastyToast.makeText(getActivity(), "No list available", Toast.LENGTH_LONG,TastyToast.CONFUSING).show();
                list.setText("No list available");
            }
        }catch (Exception e){}



    }
    private String getNo(RandomAccessFile raf){

        int val = 0;
        String v = "";
        try {
            v = raf.readUTF();
            if (!(v.length() > 0)) {
                return "No";
            } else
                return v;

        }
        catch (Exception e){
            val = 1;
        }
        if(val == 1){
            return  "No";
        }
        else {
            return  v;
        }
    }

}
