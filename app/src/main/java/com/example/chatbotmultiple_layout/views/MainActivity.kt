package com.example.chatbotmultiple_layout.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbotmultiple_layout.R
import com.example.chatbotmultiple_layout.adapter.DataAdapter
import com.example.chatbotmultiple_layout.databinding.ActivityMainBinding
import com.example.chatbotmultiple_layout.extra.Constants
import com.example.chatbotmultiple_layout.viewmodel.DataViewModel

class MainActivity : AppCompatActivity(), Clicklistener {


    private lateinit var mainViewModel: DataViewModel
    var list = ArrayList<Chatmessage>()
    lateinit var mainAdapter : DataAdapter
    var adapterpos:Int= 0
    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        list= arrayListOf(Chatmessage("Hi custmer i am fidoo","how can i help you","tell me", null))

        activityMainBinding.tvmain1.text= list.get(adapterpos).msg1
        activityMainBinding.tvmain2.text= list.get(adapterpos).msg2
        activityMainBinding.tvmain3.text= list.get(adapterpos).msg3



        activityMainBinding.button1.setOnClickListener {
            list.clear()
            adapterpos=0
            Constants.submitclickcount=0
            Constants.cancelclickcount=0
            mainViewModel.getcancelstatus("10","1a9eddfe5e5b2c75f6c15e739e05ab46","100002")
            setchat(null,null, null, "${activityMainBinding.button1.text}" )
            setRecyclerAdaptor()

            adapterpos++


            setchat("item canceled","refund tomorrow", "any help", null )
            mainAdapter.notifyItemChanged(adapterpos)
            if (activityMainBinding.recycler !=null){
                activityMainBinding.button1.isClickable= false
            }
        }


        activityMainBinding.button2.setOnClickListener {
            list.clear()
            adapterpos=0
            Constants.submitclickcount=0
            Constants.cancelclickcount=0

            mainViewModel.getorderstatus("9","752b1a44c8f596b65b98305627481261","100011")
            setchat(null,null,null,"${activityMainBinding.button2.text}")
            adapterpos++
            setRecyclerAdaptor()

            mainViewModel.statuslivedata.observe(this, Observer {
                adapterpos++
                setchat("${it.msg1}", "${it.msg2}", "any help", null)
                mainAdapter.notifyItemChanged(adapterpos)
            })
            if (activityMainBinding.recycler.size !=null){
                activityMainBinding.button2.isClickable= false
            }
        }


    }
    fun setRecyclerAdaptor() {
        mainAdapter = DataAdapter(this,list, this)
        val linearLayoutManager = LinearLayoutManager(this)
        activityMainBinding.recycler.apply {
            adapter = mainAdapter
            layoutManager = linearLayoutManager
        }
    }

    fun setchat(msg1:String?,msg2:String?, msg3:String?, msg4:String?){
        var chat= Chatmessage(msg1,msg2,msg3, msg4)
        list.add(chat)
    }

    override fun onCancelClick() {

        if (Constants.cancelclickcount>=1 || Constants.submitclickcount>=1){

        }else {
            Constants.cancelclickcount++
            adapterpos++
            setchat(null, null, null, "NO")
            mainAdapter.notifyItemChanged(adapterpos)
            setchat("apology", "Thank you", null, null)

            adapterpos++
            mainAdapter.notifyItemChanged(adapterpos)
        }

    }

    override fun onSubmitClick() {


        // Constants.submitclickcount++
        if (Constants.submitclickcount>=1 || Constants.cancelclickcount>=1){


        }else {
            Constants.submitclickcount++
            setchat(null, null, null, "Yes thanks")
            adapterpos++
            mainAdapter.notifyItemChanged(adapterpos)
            adapterpos++
            setchat("thanks for reaching", null, null, null)
            mainAdapter.notifyItemChanged(adapterpos)
        }

    }
}