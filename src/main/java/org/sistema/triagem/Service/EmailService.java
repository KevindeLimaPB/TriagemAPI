package org.sistema.triagem.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private final RestClient restClient;
    private final String remetenteEmail;
    private final String remetenteNome;
    private final String apiKey;

    public EmailService(
            @Value("${app.mail.from}") String remetenteEmail,
            @Value("${app.mail.from-name}") String remetenteNome,
            @Value("${brevo.api.key}") String apiKey) {
        this.remetenteEmail = remetenteEmail;
        this.remetenteNome = remetenteNome;
        this.apiKey = apiKey;
        this.restClient = RestClient.create();
    }

    public void enviarCodigoRecuperacao(String destinatario, String codigo) {
        String htmlContent = """
            <div style="font-family: Arial, sans-serif; padding: 20px;">
                <h2>MedTriage — Recuperação de Senha</h2>
                <p>Olá,</p>
                <p>Você solicitou a alteração de senha da sua conta.</p>
                <p>Seu código de verificação é:</p>
                <h1 style="color: #2563eb; letter-spacing: 2px;">%s</h1>
                <p>Este código expira em 10 minutos e pode ser usado uma única vez.</p>
                <p>Se você não solicitou, ignore este e-mail.</p>
                <br>
                <p>— Equipe MedTriage</p>
            </div>
            """.formatted(codigo);

        Map<String, Object> body = Map.of(
                "sender", Map.of("name", remetenteNome, "email", remetenteEmail),
                "to", List.of(Map.of("email", destinatario)),
                "subject", "MedTriage — Código de recuperação de senha",
                "htmlContent", htmlContent
        );

        try {
            restClient.post()
                    .uri("https://api.brevo.com/v3/smtp/email")
                    .header("api-key", apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail via API do Brevo: " + e.getMessage(), e);
        }
    }
}