package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.PasswordResetToken;
import ra.model.repository.PasswordResetTokenRepository;
import ra.model.service.PasswordResetTokenService;

@Service
public class PasswordResetTokenServiceImple implements PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Override
    public PasswordResetToken saveOrUpdate(PasswordResetToken passwordResetToken) {
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public PasswordResetToken getLastTokenByUserId(int userId) {
        return passwordResetTokenRepository.getLastTokenByUserId(userId);
    }
}
