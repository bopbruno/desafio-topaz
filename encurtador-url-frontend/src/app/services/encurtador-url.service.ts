import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { ErroResponse, UrlEncurtadaRequest, UrlEncurtadaResponse } from '../models/url.model';

@Injectable({
  providedIn: 'root',
})
export class EncurtadorURLService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/url`;

  encurtar(request: UrlEncurtadaRequest): Observable<UrlEncurtadaResponse> {
    console.log(this.baseUrl);
    return this.http
      .post<UrlEncurtadaResponse>(this.baseUrl, request)
      .pipe(catchError(this.tratarErro));
  }

  private tratarErro(erro: HttpErrorResponse) {
    const corpo = erro.error as ErroResponse | undefined;
    const mensagem =
      corpo?.message ?? 'Não foi possível se comunicar com o servidor. Verifique se o backend está no ar.';
    return throwError(() => new Error(mensagem));
  }
}