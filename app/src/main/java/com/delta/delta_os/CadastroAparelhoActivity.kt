package com.delta.delta_os

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.delta.delta_os.bean.Session
import com.delta.delta_os.db.DbManager
import com.delta.delta_os.util.Util
import kotlinx.android.synthetic.main.activity_cadastro_aparelho.*
import kotlinx.android.synthetic.main.activity_cadastro_aparelho.modeloText
import kotlinx.android.synthetic.main.activity_cadastro_aparelho.serialText
import kotlinx.android.synthetic.main.activity_cadastro_aparelho.valorText




class CadastroAparelhoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_aparelho)
        this.setTitle("Cadastro de Aparelho");

        salvarButton.setOnClickListener {
            var dbManager = DbManager(this)
            var values = ContentValues()

            values.put("nome", equipamentoText.text.toString())

            if (equipamentoText.text.toString().equals("")) {
                Toast.makeText(this, "Nome do Aparelho é obrigatório!", Toast.LENGTH_LONG).show()
            } else {

                if (modeloText.text.toString().equals("")) {
                    values.put("modelo", "")
                } else {
                    values.put("modelo", modeloText.text.toString())
                }
                if (serialText.text.toString().equals("")) {
                    values.put("serial", "")
                } else {
                    values.put("serial", serialText.text.toString())
                }
                if (valorText.text.toString().equals("")) {
                    values.put(
                        "valor",
                        0.0
                    );
                } else {
                    values.put(
                        "valor",
                        valorText.text.toString().toDouble()
                    );
                }
                values.put("idCliente", Session.idLocalCadAparelho);
                values.put("uuidCliente", Session.uuidCliente);
                values.put("pronto", "NAO_PRONTO")
                values.put("entregue", "NAO_ENTREGUE")
                values.put("dataSaida", "dataSaida")
                values.put("devolucao", "NAO")
                values.put("defeito_obs", "")
                val date = Util().getCurrentDateTime()
                values.put("dataEntrada", Util().toSimpleString(date))
                values.put("idServidor", 0)
                var ID = dbManager.InsertAparelho(values);
               // Session.idLocalCadAparelho = ID.toInt()
                Toast.makeText(this, "Aparelho inserido com sucesso!", Toast.LENGTH_LONG).show()


                var intent = Intent(this, MainAparelhoActivity::class.java)

                startActivity(intent)
            }
        }


    }
}
