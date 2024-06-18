package dev.arena.book_network.services.email.account;

import dev.arena.book_network.constants.Constants;
import dev.arena.book_network.entities.Account;
import dev.arena.book_network.enums.EmailTemplateName;
import dev.arena.book_network.services.token.TokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountEmailServiceImplementation implements AccountEmailService {

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;


    private final TokenService tokenService;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    public void sendValidationEmail(Account account) throws MessagingException {
        String generatedToken = tokenService.generateAndSaveActivationToken(account);
        sendEmail(
                account.getEmail(),
                account.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                generatedToken,
                Constants.ACTIVATE_ACCOUNT_SUBJECT
        );
    }

    @Async
    private void sendEmail(
            String to,
            String accountName,
            EmailTemplateName emailTemplateName,
            String activationUrl,
            String activationCode,
            String subject
    ) throws MessagingException {

        String templateName;
        if (emailTemplateName == null) {
            templateName = "confirm-email";
        } else {
            templateName = emailTemplateName.getName();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name()
        );

        Map<String, Object> variables = new HashMap<>();
        variables.put("accountName", accountName);
        variables.put("activationUrl", activationUrl);
        variables.put("activationCode", activationCode);

        Context context = new Context();
        context.setVariables(variables);

        String htmlTemplate = springTemplateEngine.process(templateName, context);
        helper.setText(htmlTemplate, true);
        helper.setFrom("no-reply@markarena.com");
        helper.setSubject(subject);
        helper.setTo(to);

        mailSender.send(mimeMessage);
    }
}
