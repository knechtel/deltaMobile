package com.delta.delta_os

class Cliente{
    var id:Int?=null;
    var nome:String?=null;
    var cpf:String?=null;
    var endereco:String?=null;
    var email:String?=null;
    var telefone:String?=null;

    constructor(Id:Int,nome:String,cpf:String,endereco:String,email:String,telefone:String){
        this.id=id;
        this.nome=nome;
        this.cpf=cpf;
        this.endereco=endereco;
        this.email=email;
        this.telefone=telefone;

    }
}