import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '../clientes/clientes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(private httpClient: HttpClient) { }

  getCliente(): Cliente{
    let cliente:Cliente = new Cliente();
    cliente.nome = 'Edwin Lima';
    cliente.cpf = '07232244444'

    return cliente;
  }

  salvar(cliente:Cliente):Observable<Cliente>{
    return this.httpClient.post<Cliente>('http://localhost:8080/cliente',cliente);
  }

  listar():Observable<Cliente[]>{
    return this.httpClient.get<Cliente[]>('http://localhost:8080/cliente');
  }

}
