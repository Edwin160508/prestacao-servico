import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
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
  id: number;

  constructor(
    private service:ClientesService,
    private router:Router,
    private activatedRouter: ActivatedRoute
    ) { 
    this.cliente = new Cliente();
    
  }

  ngOnInit(): void {
    let params = this.activatedRouter.params;
    if(params && params.value && params.value.id){
      this.id = params.value.id;
      this.buscarClientePeloId(this.id);
    }
  }

  buscarClientePeloId(id:number){
    this.service.buscarClientePeloId(id).subscribe(response=>{
      this.cliente = response;
    },erroResonse =>{
      this.cliente = new Cliente();
    });
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
