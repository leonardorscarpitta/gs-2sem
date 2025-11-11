// VitaFlow - Enhanced JavaScript

// Máscara para CPF
function mascaraCPF(input) {
    let value = input.value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    }
    input.value = value;
}

// Inicialização quando o DOM estiver pronto
document.addEventListener('DOMContentLoaded', function() {
    // Aplicar máscara ao campo CPF
    const cpfInput = document.getElementById('cpf');
    if (cpfInput) {
        cpfInput.addEventListener('input', function() {
            mascaraCPF(this);
        });
    }

    // Auto-hide alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'all 0.3s ease';
            alert.style.opacity = '0';
            alert.style.transform = 'translateY(-10px)';
            setTimeout(() => alert.remove(), 300);
        }, 5000);
    });

    // Adicionar confirmação visual em botões de submit
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const submitBtn = form.querySelector('button[type="submit"]');
            if (submitBtn && !submitBtn.disabled) {
                submitBtn.disabled = true;
                submitBtn.style.opacity = '0.6';
                submitBtn.style.cursor = 'not-allowed';

                // Reativar após 3 segundos caso haja erro
                setTimeout(() => {
                    submitBtn.disabled = false;
                    submitBtn.style.opacity = '1';
                    submitBtn.style.cursor = 'pointer';
                }, 3000);
            }
        });
    });

    // Adicionar efeito de focus nos inputs
    const inputs = document.querySelectorAll('.form-control');
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.parentElement.style.transform = 'translateY(-2px)';
            this.parentElement.style.transition = 'transform 0.2s ease';
        });

        input.addEventListener('blur', function() {
            this.parentElement.style.transform = 'translateY(0)';
        });
    });

    // Validação de qualidade (0-10)
    const qualidadeInputs = document.querySelectorAll('input[type="number"][max="10"]');
    qualidadeInputs.forEach(input => {
        input.addEventListener('input', function() {
            const value = parseInt(this.value);
            if (value < 0) this.value = 0;
            if (value > 10) this.value = 10;

            // Adicionar feedback visual
            if (value >= 0 && value <= 3) {
                this.style.borderColor = '#ef4444';
            } else if (value >= 4 && value <= 6) {
                this.style.borderColor = '#f59e0b';
            } else if (value >= 7 && value <= 10) {
                this.style.borderColor = '#10b981';
            }
        });
    });
});
