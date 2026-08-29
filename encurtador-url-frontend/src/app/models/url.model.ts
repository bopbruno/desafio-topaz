export interface ErroResponse {
  code: string;
  message: string;
}

export interface UrlEncurtadaRequest {
  urlOriginal: string;
  alias?: string | null;
}

export interface UrlEncurtadaResponse {
  urlEncurtada: string;
  urlOriginal: string;
}