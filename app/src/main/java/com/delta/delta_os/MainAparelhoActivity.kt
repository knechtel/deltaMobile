package com.delta.delta_os

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.delta.delta_os.bean.Aparelho
import com.delta.delta_os.bean.Session
import com.delta.delta_os.db.DbManager
import com.delta.delta_os.editAparelho.EditAparelhoActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_aparelho.*
import kotlinx.android.synthetic.main.ticcket.view.*
import java.util.Date

class MainAparelhoActivity : AppCompatActivity() {
    var listAparelho = ArrayList<Aparelho>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_aparelho)
        this.setTitle("Aparelhos  "+Session.uuidCliente);

        var dbManager = DbManager(this);
        //var idCliente = savedInstanceState!!.getString("idCliente");
        //var bundle :Bundle ?=intent.extras


        listAparelho = dbManager.LoadQueryAparelhoByIdClienteuuid(Session.uuidCliente)
        if(listAparelho!=null){
            var myNotesAdapter = MyAparelhoAdapter(this, listAparelho)
            lvAparelhos.adapter = myNotesAdapter
        }else{
            Toast.makeText(
                this,
                " edit! = nullo listAparelhos" + Session.Companion.idLocalAparelhos,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.addNote -> {
                    //Got to add paage
                    var intent = Intent(this, CadastroAparelhoActivity::class.java)
                    startActivity(intent)
                }
                R.id.addRefresh -> {

                    var dbManager = DbManager(this);
                    listAparelho = dbManager.LoadQueryAparelho()
                    var myNotesAdapter = MyAparelhoAdapter(this, listAparelho)
                    lvAparelhos.adapter = myNotesAdapter
                    Toast.makeText(
                        this,
                        "  ID = "+Session.Companion.idCliente ,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class MyAparelhoAdapter : BaseAdapter {
        var listAparelhoAdapter = ArrayList<Aparelho>();
        var context: Context? = null

        constructor(context: Context, lisAparelhoAdapter: ArrayList<Aparelho>) : super() {
            this.context = context;
            this.listAparelhoAdapter = lisAparelhoAdapter;
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticcket, null)
            var myVCliente = listAparelhoAdapter[position]
            myView.tvTitle.text = myVCliente.nome;
            myView.tvDes.text = "id local "+myVCliente.id.toString()+ "\nidServidor "+myVCliente.idServidor+
            "\nidCliente "+myVCliente.idCliente+
            "\nuuid : "+myVCliente.uuidCliente;

            myView.ivEdit.setOnClickListener({
                var intent = Intent(this.context, EditAparelhoActivity::class.java)
//              intent.putExtra("idLocal",myVCliente.id)
                Session.idLocalCadAparelho = myVCliente.idCliente!!.toInt()
                startActivity(intent)
            })

            return myView;
        }

        override fun getItem(position: Int): Any {
            return listAparelhoAdapter[position];
        }

        override fun getItemId(position: Int): Long {
           return position.toLong();

        }

        override fun getCount(): Int {
            return listAparelhoAdapter.size
        }

    }
}
