
package sv.com.edu.udb.dsm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sv.com.udb.dsm.R;

public class EcuacionActivity extends AppCompatActivity {

    HomeActivity user = HomeActivity.getInstance();


    public static final String EMPTY = "";
    private EditText txtA;
    private EditText txtB;
    private EditText txtC;
    private TextView lblX1;
    private TextView lblX2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecuacion);
        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        txtC = findViewById(R.id.txtC);
        lblX1 = findViewById(R.id.lblX1);
        lblX2 = findViewById(R.id.lblX2);
    }

    public void resolveEcuation(View view){
        try{
            validateInput(txtA,txtB,txtC);
            float a = Integer.parseInt(txtA.getText().toString());
            float b = Integer.parseInt(txtB.getText().toString());
            float c = Integer.parseInt(txtC.getText().toString());
            if(a == 0){
                throw new Exception("\"A\" no puede ser cero");
            }
            // Calcular discrimante; b^2-4ac, abs para calcular las no reales despues
            double d = b * b - 4 * a * c;
            double sqrtVal = Math.sqrt(Math.abs(d));
            String[] roots;
            if(d >= 0){
                roots = calculateRealRoots(a,b,c,sqrtVal);
            }else {
                roots = calculateNonRealRoots(a,b,c,sqrtVal);
            }
            updateRoots(roots);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void updateRoots(String[] roots){
        lblX1.setText(Html.fromHtml("X<sub>1</sup> : " + roots[0]));
        lblX2.setText(Html.fromHtml("X<sub>2</sup> : " + roots[1]));
        user.printName();
    }

    private static String[] calculateRealRoots(float a, float b, float c, double d) {
        //-b+-sqrt(b^2-4ac) / 2a
      double x1  = (-b + d) / (2*a);
      double x2  = (-b - d) / (2*a);
      return new String[]{String.valueOf(x1), String.valueOf(x2)};
    }

    private static String[] calculateNonRealRoots(float a,float b, float c, double d) {
        String x1 = -b / (2*a) + "+" +  d / (2*a)  + "i" ;
        String x2 = -b / (2*a) + "-" +  d / (2*a)  + "i" ;
        return new String[]{x1,x2};
    }

    private void validateInput(EditText... editTexts) throws Exception {
        for(EditText editText: editTexts) {
            String val = editText.getText().toString();
            if(EMPTY.equals(val)){
                throw new Exception(editText.getContentDescription().toString() + " esta vacios los campos");
            }
        }
    }

}