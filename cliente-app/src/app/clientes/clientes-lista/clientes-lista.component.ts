import { Component, OnInit } from '@angular/core';
import { Cliente } from '../clientes';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = [];
  constructor(private service:ClientesService) { }

  ngOnInit(): void {
    this.listar();  
  }

  listar(){
    this.service.listar().subscribe(response =>{
      this.clientes = response;
    });
  }

}
