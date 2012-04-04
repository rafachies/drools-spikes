[condition] [InsuranceProposal] cliente idade velho = $proposal: InsuranceProposal( age > 60);
[condition] [InsuranceProposal] cliente idade adulto = $proposal: InsuranceProposal( age >= 25, age < 60);
[condition] [InsuranceProposal] cliente idade jovem = $proposal: InsuranceProposal( age < 25);

[condition] [InsuranceProposal] cliente localidade interior = $proposal: InsuranceProposal( locale == Locale.COUNTRY_SIDE);
[condition] [InsuranceProposal] cliente localidade capital = $proposal: InsuranceProposal( locale == Locale.CAPITAL);

[condition] [InsuranceProposal] carro tipo popular = $proposal: InsuranceProposal( carType == CarType.POPULAR);
[condition] [InsuranceProposal] carro tipo sedan = $proposal: InsuranceProposal( carType == CarType.SEDAN);

[condition] [InsuranceProposal] cliente scpc positivo = $proposal: InsuranceProposal( scpcConstraint == true);
[condition] [InsuranceProposal] cliente scpc negativo = $proposal: InsuranceProposal( scpcConstraint == false);

[condition] [InsuranceProposal] cliente estado solteiro = $proposal: InsuranceProposal( married == false);
[condition] [InsuranceProposal] cliente estado casado = $proposal: InsuranceProposal( married == true);

[consequence][InsuranceProposal] score decrementa {value} = $proposal.decrementScore({value});
[consequence][InsuranceProposal] score incrementa {value} = $proposal.incrementScore({value});
