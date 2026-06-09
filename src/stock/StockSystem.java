package stock;
import java.util.ArrayList;
import java.util.Scanner;

public class StockSystem {
    
	private static ArrayList<Product> productList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;
    
    
	public static void main(String[] args) {
	
		int option = 0;

        // Produtos iniciais para teste
//        productList.add(new Product(idCounter++, "Arroz 5kg", 450.00, 20));
        productList.add(new Product(idCounter++, "Óleo da Malta 1L", 130.00, 15));

        //Main Menu Loop
         do {
            System.out.println("\n============= GESTÃO DE STOCK (ISUTC) =============");
            System.out.println("1. Registar Novo Produto");
            System.out.println("2. Visualizar Stock Atual");
            System.out.println("3. Simular Venda");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } else {
                System.out.println("Opção inválida! Digite apenas números.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            switch (option) {
                case 1:
                    registerProduct();
                    break;
                case 2:
                    viewStock();
                    break;
                case 3:
                    simulateSale();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema... Até à próxima!");
                    break;
                default:
                    System.out.println("Opção incorreta. Tente novamente.");
            }
        } while (option != 4);
    }

    // Método para Registar Produto
    private static void registerProduct() {
        System.out.println("\n--- REGISTAR PRODUTO ---");
        System.out.print("Nome do Produto: ");
        String name = scanner.nextLine();

        System.out.print("Preço (MZN): ");
        double price = scanner.nextDouble();

        System.out.print("Quantidade Inicial em Stock: ");
        int quantity = scanner.nextInt();

        Product newProduct = new Product(idCounter++, name, price, quantity);
        productList.add(newProduct);

        System.out.println("🎉 Produto '" + name + "' registado com sucesso! (ID: " + (idCounter - 1) + ")");
    }

    // Método para Listar Stock
    private static void viewStock() {
        System.out.println("\n--- STOCK ATUAL ---");
        if (productList.isEmpty()) {
            System.out.println("O stock está completamente vazio.");
            return;
        }

        System.out.printf("%-5s | %-20s | %-12s | %-10s\n", "ID", "Nome", "Preço", "Quantidade");
        System.out.println("---------------------------------------------------------");
        
        for (Product p : productList) {
            System.out.printf("%-5d | %-20s | %-10.2f MZN | %-10d %s\n", 
                p.getId(), 
                p.getName(), 
                p.getPrice(), 
                p.getQuantity(),
                (p.getQuantity() <= 5 ? "⚠️ (Stock Baixo!)" : "")
            );
        }
    }

    // Método para Simular Venda
    private static void simulateSale() {
        System.out.println("\n--- SIMULAR VENDA ---");
        System.out.print("Digite o ID do produto vendido: ");
        int searchedId = scanner.nextInt();

        Product foundProduct = null;
        for (Product p : productList) {
            if (p.getId() == searchedId) {
                foundProduct = p;
                break;
            }
        }

        if (foundProduct == null) {
            System.out.println("❌ Erro: Produto com o ID " + searchedId + " não foi encontrado.");
            return;
        }

        System.out.print("Digite a quantidade a vender: ");
        int saleQuantity = scanner.nextInt();

        if (saleQuantity <= 0) {
            System.out.println("❌ Quantidade inválida para venda.");
        } else if (saleQuantity > foundProduct.getQuantity()) {
            System.out.println("❌ Venda cancelada! Stock insuficiente. Apenas restam " + foundProduct.getQuantity() + " unidades.");
        } else {
            double totalToPay = saleQuantity * foundProduct.getPrice();
            foundProduct.reduceStock(saleQuantity);
            
            System.out.println("\n✅ VENDA REALIZADA COM SUCESSO!");
            System.out.println("Artigo: " + foundProduct.getName());
            System.out.println("Total a Pagar: " + totalToPay + " MZN");
            System.out.println("Stock atualizado de " + foundProduct.getName() + ": " + foundProduct.getQuantity() + " unidades.");
        }

	}

}
