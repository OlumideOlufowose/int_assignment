describe('E-commerce Site Testing', () => {
    beforeEach(() => {
    // Visit website
        cy.visit('https://www.jumia.com.ng');

    });

    it("add product to cart", { retries: 2 },  () => {

        // Locate search box and search for product
        cy.log("Locate search box and search for product");
        cy.get("#fi-q").should('be.visible').type("bag");
        cy.get('.btn._prim._md.-mls.-fsh0').should('be.visible').click();

        // Add product to cart
        cy.log("Add Product to cart")
        cy.get(".img[data-src='https://ng.jumia.is/unsafe/fit-in/300x300/filters:fill(white)/product/64/950456/1.jpg?0716']").click();
        cy.get("#add-to-cart > button").should('have.text',"Add to cart").click();

        // Proceed to checkout
        cy.log("Proceed to checkout")
        cy.get(".-df.-i-ctr.-gy9.-hov-or5.-phs.-fs16").should('be.visible').click();
        cy.get("#jm > header > section > div.col.-df.-j-bet.-m.-phn.-i-ctr > a").should('be.visible').click()
        cy.get("#jm > main > div > div.col4 > div > article > div.-fs0.-pas.-bt > a").should('be.visible').click()
        cy.get('#identification-form > div.ctx-identification.flow-layout > div.center > h2').should('have.text', "Welcome to Jumia")
    })
});