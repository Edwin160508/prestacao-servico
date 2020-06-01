import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '../clientes/clientes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(private httpClient: HttpClient) { }

  buscarClientePeloId(id:number): Observable<Cliente>{    
    return this.httpClient.get<Cliente>(`http://localhost:8080/cliente/${id}`);
  }

  salvar(cliente:Cliente):Observable<Cliente>{
    return this.httpClient.post<Cliente>('http://localhost:8080/cliente',cliente);
  }

  listar():Observable<Cliente[]>{
    return this.httpClient.get<Cliente[]>('http://localhost:8080/cliente');
  }

}
