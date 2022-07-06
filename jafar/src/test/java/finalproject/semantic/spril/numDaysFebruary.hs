import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 1) regF 
    , Jump (Abs 63) 
    , Nop 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regB reg0 regD 
    , Branch regD (Abs 174) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 19) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 15) 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute NEq regA regB regA 
    , Push regA 
    , Pop regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 41) 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 28) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 50) 
    , Nop 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 29) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue (-3)) regC 
    , Compute Add regF regC regC 
    , Pop regA 
    , Store regA (IndAddr regC) 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Load (ImmValue 2020) regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 77) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 2021) regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 99) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 2022) regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 121) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 2023) regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 143) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 2024) regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 165) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

