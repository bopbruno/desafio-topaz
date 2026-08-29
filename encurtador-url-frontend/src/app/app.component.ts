import { Component, inject, signal } from '@angular/core';
import { ErroResponse, UrlEncurtadaRequest, UrlEncurtadaResponse } from './models/url.model';
import { EncurtadorURLService } from './services/encurtador-url.service'
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  private readonly fb = inject(FormBuilder);
  private readonly encurtadorUrlService = inject(EncurtadorURLService);

  title = 'encurtador-url-frontend';
  protected readonly ultimoResultado = signal<String | null>(null);
  protected readonly erro = signal<string | null>(null);

  protected readonly form = this.fb.nonNullable.group({
    urlOriginal: [''],
    alias: [''],
  });

  submeter(): void {

    this.erro.set(null);
    this.ultimoResultado.set(null);

    const { urlOriginal, alias } = this.form.getRawValue();    
    this.encurtadorUrlService
    .encurtar({urlOriginal, alias})
    .subscribe({
      next: (resposta) => {
        this.ultimoResultado.set(resposta.urlEncurtada);
      },
      error: (err: Error) => {
        this.erro.set(err.message);
      }
    });
  }

}
