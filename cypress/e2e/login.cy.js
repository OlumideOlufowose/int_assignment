describe('Login Functionality', () => 
  
  {
    beforeEach(() => {
      cy.visit('https://practicetestautomation.com/practice-test-login/');
    });
  
    it('Positive Test Case: Should login successfully with valid credentials', () => {
      
      cy.get('#username').type('student');
      cy.get('#password').type('Password123');
      cy.get('#submit').click();
      
      // Assertion for successful login
      cy.url().should('include', 'successfully');
    });
  
    it('Negative Test Case: Should display an error message with invalid credentials', () => {

      cy.get('#username').type('tester');
      cy.get('#password').type('invalidPassword');
      cy.get('#submit').click();
      
      // Assersion to check for error message
      cy.get('#error').should('include.text', 'invalid').should('be.visible');
    });
  });