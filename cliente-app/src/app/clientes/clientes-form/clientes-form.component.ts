import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from '../clientes';
import { ClientesService } from '../../services/clientes.service';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  erros: string[];

  constructor(
    private service:ClientesService,
    private router:Router
    ) { 
    this.cliente = new Cliente();
    
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.service
    .salvar(this.cliente)
    .subscribe(response => {
      console.log(response);
      this.success = true;
      this.erros = null;
      this.cliente = response;
    }, errorResponse =>{
      this.success = false;
      this.erros = errorResponse.error;
      console.log(this.erros);      
    });
  }

  voltarParaListagem(){
    this.router.navigate(['/clientes-lista']);
  }
}
