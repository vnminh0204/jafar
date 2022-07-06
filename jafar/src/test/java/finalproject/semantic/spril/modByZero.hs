import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue (-1)) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regB reg0 regD 
    , Branch regD (Abs 63) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 17) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 13) 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regB reg0 regD 
    , Branch regD (Abs 63) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 37) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 33) 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 8) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regB reg0 regD 
    , Branch regD (Abs 63) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 57) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 53) 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

