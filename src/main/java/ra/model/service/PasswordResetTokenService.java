package ra.model.service;

import ra.model.entity.PasswordResetToken;

public interface PasswordResetTokenService {
    PasswordResetToken saveOrUpdate(PasswordResetToken passwordResetToken);
    PasswordResetToken getLastTokenByUserId(int userId);
}
